import java.util.Scanner;

abstract class Person {
    private int pid;
    private String name;
    private String role;
    private String department;

    Person(int pid, String name, String role) {
        this.pid = pid;
        this.name = name;
        this.role = role;
        this.department = "";
    }

    public int getid() { return pid; }
    public String getname() { return name; }
    public String getrole() { return role; }
    public String getDepartment() { return department; }

    abstract void viewdetails();
    abstract void managetasks();
}

class Principal extends Person {
    // VP (only one)
    static int[] vpid = new int[1];
    static String[] vpname = new String[1];
    static int vpcount = 0;

    // HODs
    static int[] hodid = new int[50];
    static String[] hodname = new String[50];
    static String[] hoddept = new String[50];
    static int hodcount = 0;

    // Faculty
    static int[] faculid = new int[50];
    static String[] faculname = new String[50];
    static String[] faculdept = new String[50];
    static int faculcount = 0;

    // Students
    static int[] studid = new int[200];
    static String[] studname = new String[200];
    static String[] studdept = new String[200];
    static int studcount = 0;

    // Student academic data
    static double[] studMarks = new double[200];
    static int[] studAttendance = new int[200];

    // Faculty assignments (one assignment string per faculty index)
    static String[] facAssignment = new String[50];

    Principal(int pid, String name) {
        super(pid, name, "Principal");
    }

    void viewdetails() {
        System.out.println("\n--- All Records ---");
        System.out.println("Principal: " + getname() + " (ID:" + getid() + ")");
        System.out.println("Vice Principal: " + (vpcount > 0 ? vpname[0] + " (ID:" + vpid[0] + ")" : "None"));

        System.out.println("\nHODs:");
        if (hodcount == 0) System.out.println("None");
        for (int i = 0; i < hodcount; i++)
            System.out.println("- " + hodname[i] + " (" + hoddept[i] + ") ID:" + hodid[i]);

        System.out.println("\nFaculty:");
        if (faculcount == 0) System.out.println("None");
        for (int i = 0; i < faculcount; i++)
            System.out.println("- " + faculname[i] + " (" + faculdept[i] + ") ID:" + faculid[i]
                    + (facAssignment[i] != null ? " [Assignment: " + facAssignment[i] + "]" : ""));

        System.out.println("\nStudents:");
        if (studcount == 0) System.out.println("None");
        for (int i = 0; i < studcount; i++)
            System.out.println("- " + studname[i] + " (" + studdept[i] + ") ID:" + studid[i]
                    + " Marks:" + studMarks[i] + " Attendance:" + studAttendance[i]);
    }

    void managetasks() {
        System.out.println("Principal can add, update, delete VP, HOD, Faculty, Students.");
    }

    // Unique ID check across all roles
    static boolean isUniqueID(int id) {
        if (vpcount > 0 && vpid[0] == id) return false;
        for (int i = 0; i < hodcount; i++) if (hodid[i] == id) return false;
        for (int i = 0; i < faculcount; i++) if (faculid[i] == id) return false;
        for (int i = 0; i < studcount; i++) if (studid[i] == id) return false;
        return true;
    }

    // VP CRUD
    void addVP(int vid, String name) {
        if (vpcount >= 1) { System.out.println("Vice Principal already exists!"); return; }
        if (!isUniqueID(vid)) { System.out.println("Duplicate ID not allowed!"); return; }
        vpid[0] = vid; vpname[0] = name; vpcount = 1;
        System.out.println("VP added: " + name);
    }
    void updateVP(int vid, String newName) {
        if (vpcount == 0) { System.out.println("No VP to update."); return; }
        if (vpid[0] != vid) { System.out.println("VP ID not found."); return; }
        vpname[0] = newName; System.out.println("VP updated: " + newName);
    }
    void deleteVP(int vid) {
        if (vpcount == 0) { System.out.println("No VP to delete."); return; }
        if (vpid[0] != vid) { System.out.println("VP ID not found."); return; }
        vpcount = 0; vpname[0] = null; vpid[0] = 0;
        System.out.println("VP deleted.");
    }

    // HOD CRUD
    void addHOD(int hid, String name, String dept) {
        if (!isUniqueID(hid)) { System.out.println("Duplicate ID not allowed!"); return; }
        hodid[hodcount] = hid; hodname[hodcount] = name; hoddept[hodcount] = dept; hodcount++;
        System.out.println("HOD added: " + name);
    }
    void updateHOD(int hid, String newName, String newDept) {
        int idx = findIndex(hodid, hodcount, hid);
        if (idx == -1) { System.out.println("HOD ID not found."); return; }
        hodname[idx] = newName; hoddept[idx] = newDept; System.out.println("HOD updated: " + newName);
    }
    void deleteHOD(int hid) {
        int idx = findIndex(hodid, hodcount, hid);
        if (idx == -1) { System.out.println("HOD ID not found."); return; }
        for (int i = idx; i < hodcount - 1; i++) {
            hodid[i] = hodid[i + 1];
            hodname[i] = hodname[i + 1];
            hoddept[i] = hoddept[i + 1];
        }
        hodcount--; System.out.println("HOD deleted.");
    }

    // Faculty CRUD
    void addFaculty(int fid, String name, String dept) {
        if (!isUniqueID(fid)) { System.out.println("Duplicate ID not allowed!"); return; }
        faculid[faculcount] = fid; faculname[faculcount] = name; faculdept[faculcount] = dept; facAssignment[faculcount] = null; faculcount++;
        System.out.println("Faculty added: " + name);
    }
    void updateFaculty(int fid, String newName, String newDept) {
        int idx = findIndex(faculid, faculcount, fid);
        if (idx == -1) { System.out.println("Faculty ID not found."); return; }
        faculname[idx] = newName; faculdept[idx] = newDept; System.out.println("Faculty updated: " + newName);
    }
    void deleteFaculty(int fid) {
        int idx = findIndex(faculid, faculcount, fid);
        if (idx == -1) { System.out.println("Faculty ID not found."); return; }
        for (int i = idx; i < faculcount - 1; i++) {
            faculid[i] = faculid[i + 1];
            faculname[i] = faculname[i + 1];
            faculdept[i] = faculdept[i + 1];
            facAssignment[i] = facAssignment[i + 1];
        }
        faculcount--; System.out.println("Faculty deleted.");
    }

    // Student CRUD
    void addStudent(int sid, String name, String dept) {
        if (!isUniqueID(sid)) { System.out.println("Duplicate ID not allowed!"); return; }
        studid[studcount] = sid; studname[studcount] = name; studdept[studcount] = dept;
        studMarks[studcount] = 0.0; studAttendance[studcount] = 0;
        studcount++;
        System.out.println("Student added: " + name);
    }
    void updateStudent(int sid, String newName, String newDept) {
        int idx = findIndex(studid, studcount, sid);
        if (idx == -1) { System.out.println("Student ID not found."); return; }
        studname[idx] = newName; studdept[idx] = newDept; System.out.println("Student updated: " + newName);
    }
    void deleteStudent(int sid) {
        int idx = findIndex(studid, studcount, sid);
        if (idx == -1) { System.out.println("Student ID not found."); return; }
        for (int i = idx; i < studcount - 1; i++) {
            studid[i] = studid[i + 1];
            studname[i] = studname[i + 1];
            studdept[i] = studdept[i + 1];
            studMarks[i] = studMarks[i + 1];
            studAttendance[i] = studAttendance[i + 1];
        }
        studcount--; System.out.println("Student deleted.");
    }

    // Student academic setters/getters
    boolean setStudentRecord(int sid, double marks, int attendance) {
        int idx = findIndex(studid, studcount, sid);
        if (idx == -1) return false;
        studMarks[idx] = marks;
        studAttendance[idx] = attendance;
        return true;
    }
    int findStudentIndex(int id) { return findIndex(studid, studcount, id); }

    // Assign work to faculty by faculty id
    boolean assignWorkToFaculty(int facId, String work) {
        int idx = findIndex(faculid, faculcount, facId);
        if (idx == -1) return false;
        facAssignment[idx] = work;
        return true;
    }

    // View faculties by department
    void viewFacultiesByDept(String dept) {
        boolean found = false;
        for (int i = 0; i < faculcount; i++) {
            if (faculdept[i].equalsIgnoreCase(dept)) {
                System.out.println("- " + faculname[i] + " ID:" + faculid[i]
                        + (facAssignment[i] != null ? " [Assignment: " + facAssignment[i] + "]" : ""));
                found = true;
            }
        }
        if (!found) System.out.println("No faculty found in department: " + dept);
    }

    // View students by department
    void viewStudentsByDept(String dept) {
        boolean found = false;
        for (int i = 0; i < studcount; i++) {
            if (studdept[i].equalsIgnoreCase(dept)) {
                System.out.println("- " + studname[i] + " ID:" + studid[i] + " Marks:" + studMarks[i] + " Attendance:" + studAttendance[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No students found in department: " + dept);
    }

    // helper to find index in arrays
    private int findIndex(int[] arr, int count, int id) {
        for (int i = 0; i < count; i++) if (arr[i] == id) return i;
        return -1;
    }
}

// VicePrincipal class
class VicePrincipal extends Person {
    private Principal principalRef;

    VicePrincipal(int pid, String name, Principal principalRef) {
        super(pid, name, "VicePrincipal");
        this.principalRef = principalRef;
    }

    void viewdetails() {
        System.out.println("\nVice Principal " + getname() + " viewing records:");
        principalRef.viewdetails();
    }

    void managetasks() {
        System.out.println("Vice Principal can add HOD, Faculty, Student (but not VP).");
    }

    void addHOD(int hid, String name, String dept) {
        if (!Principal.isUniqueID(hid)) {
            System.out.println("Error: ID " + hid + " already exists. Cannot add HOD.");
            return;
        }
        principalRef.addHOD(hid, name, dept);
    }

    void addFaculty(int fid, String name, String dept) {
        if (!Principal.isUniqueID(fid)) {
            System.out.println("Error: ID " + fid + " already exists. Cannot add Faculty.");
            return;
        }
        principalRef.addFaculty(fid, name, dept);
    }

    void addStudent(int sid, String name, String dept) {
        if (!Principal.isUniqueID(sid)) {
            System.out.println("Error: ID " + sid + " already exists. Cannot add Student.");
            return;
        }
        principalRef.addStudent(sid, name, dept);
    }
}

// HOD class
class HOD extends Person {
    private Principal principalRef;
    private String department;

    HOD(int pid, String name, String department, Principal principalRef) {
        super(pid, name, "HOD");
        this.department = department;
        this.principalRef = principalRef;
    }

    void viewdetails() {
        System.out.println("\nHOD " + getname() + " (" + department + ") viewing department records:");
        principalRef.viewFacultiesByDept(department);
        principalRef.viewStudentsByDept(department);
    }

    void managetasks() {
        System.out.println("HOD can add faculty, add students, assign work to faculty, view faculties and students by department.");
    }

    void addFaculty(int fid, String name) {
        if (!Principal.isUniqueID(fid)) {
            System.out.println("Duplicate ID not allowed! Faculty not added.");
            return;
        }
        principalRef.addFaculty(fid, name, department);
    }

    void addStudent(int sid, String name, double marks, int attendance) {
        if (!Principal.isUniqueID(sid)) {
            System.out.println("Duplicate ID not allowed! Student not added.");
            return;
        }
        principalRef.addStudent(sid, name, department);
        principalRef.setStudentRecord(sid, marks, attendance);
    }

    void assignWorkToFaculty(int facultyId, String work) {
        boolean ok = principalRef.assignWorkToFaculty(facultyId, work);
        if (!ok) System.out.println("Faculty ID not found. Cannot assign work.");
        else System.out.println("Assigned work to faculty ID " + facultyId);
    }

    void viewFaculties() {
        principalRef.viewFacultiesByDept(department);
    }

    void viewStudents() {
        principalRef.viewStudentsByDept(department);
    }
}

// Faculty class
class Faculty extends Person {
    private Principal principalRef;
    private String department;

    Faculty(int pid, String name, String department, Principal principalRef) {
        super(pid, name, "Faculty");
        this.department = department;
        this.principalRef = principalRef;
    }

    void viewdetails() {
        System.out.println("\nFaculty " + getname() + " (" + department + ")");
        System.out.println("Assigned work: " + getAssignment());
    }

    void managetasks() {
        System.out.println("Faculty can add students (with marks & attendance) and view assigned work.");
    }

    String getAssignment() {
        int idx = findFacultyIndex(getid());
        if (idx == -1) return "No assignment.";
        String a = Principal.facAssignment[idx];
        return a == null ? "No assignment." : a;
    }
    void addStudent(int sid, String name, double marks, int attendance) {
        if (!Principal.isUniqueID(sid)) {
            System.out.println("Duplicate ID not allowed! Student not added.");
            return;
        }
        principalRef.addStudent(sid, name, department);
        principalRef.setStudentRecord(sid, marks, attendance);
    }
    void viewStudentsInDept() {
        principalRef.viewStudentsByDept(department);
    }

    private int findFacultyIndex(int fid) {
        for (int i = 0; i < Principal.faculcount; i++) if (Principal.faculid[i] == fid) return i;
        return -1;
    }
}

// Student class
class Student extends Person {
    private Principal principalRef;

    Student(int pid, String name, Principal principalRef) {
        super(pid, name, "Student");
        this.principalRef = principalRef;
    }

    void viewdetails() {
        int idx = principalRef.findStudentIndex(getid());
        if (idx == -1) {
            System.out.println("Student record not found.");
            return;
        }
        System.out.println("\nStudent Details:");
        System.out.println("Name: " + Principal.studname[idx]);
        System.out.println("ID: " + Principal.studid[idx]);
        System.out.println("Department: " + Principal.studdept[idx]);
        System.out.println("Marks: " + Principal.studMarks[idx]);
        System.out.println("Attendance: " + Principal.studAttendance[idx]);
    }

    void managetasks() {
        System.out.println("Student can view their details only.");
    }
}

// Main with menu
public class Collegemanagementsystemproject{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Principal ID: ");
        int pid = readInt(sc);
        sc.nextLine();
        System.out.print("Enter Principal Name: ");
        String pname = sc.nextLine();

        Principal principal = new Principal(pid, pname);
        System.out.println("Welcome, Principal " + pname + ".");

        VicePrincipal vp = null;

        int choice;
        do {
            printMenu();
            choice = readInt(sc);
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter VP ID: ");
                    int vid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter VP Name: ");
                    String vname = sc.nextLine();
                    principal.addVP(vid, vname);
                    if (Principal.vpcount > 0) vp = new VicePrincipal(Principal.vpid[0], Principal.vpname[0], principal);
                    break;
                case 2: 
                    System.out.print("Enter HOD ID: ");
                    int hid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter HOD Name: ");
                    String hname = sc.nextLine();
                    System.out.print("Enter HOD Department: ");
                    String hdept = sc.nextLine();
                    principal.addHOD(hid, hname, hdept);
                    break;
                case 3: 
                    System.out.print("Enter existing HOD ID to login as HOD: ");
                    int loginHid = readInt(sc);
                    sc.nextLine();
                    int hidx = findIndex(Principal.hodid, Principal.hodcount, loginHid);
                    if (hidx == -1) { System.out.println("HOD ID not found."); break; }
                    HOD hod = new HOD(Principal.hodid[hidx], Principal.hodname[hidx], Principal.hoddept[hidx], principal);
                    System.out.println("Logged in as HOD " + Principal.hodname[hidx]);
                    hodMenu(sc, hod);
                    break;
                case 4: 
                    System.out.print("Enter Faculty ID: ");
                    int fid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter Faculty Name: ");
                    String fname = sc.nextLine();
                    System.out.print("Enter Faculty Department: ");
                    String fdept = sc.nextLine();
                    principal.addFaculty(fid, fname, fdept);
                    break;
                case 5: 
                    System.out.print("Enter existing Faculty ID to login as Faculty: ");
                    int loginFid = readInt(sc);
                    sc.nextLine();
                    int fidx = findIndex(Principal.faculid, Principal.faculcount, loginFid);
                    if (fidx == -1) { System.out.println("Faculty ID not found."); break; }
                    Faculty faculty = new Faculty(Principal.faculid[fidx], Principal.faculname[fidx], Principal.faculdept[fidx], principal);
                    System.out.println("Logged in as Faculty " + Principal.faculname[fidx]);
                    facultyMenu(sc, faculty);
                    break;
                case 6: // Add Student (Principal)
                    System.out.print("Enter Student ID: ");
                    int sid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Student Department: ");
                    String sdept = sc.nextLine();
                    principal.addStudent(sid, sname, sdept);
                    break;
                case 7: // Login as Student (view own details)
                    System.out.print("Enter existing Student ID to login as Student: ");
                    int loginSid = readInt(sc);
                    sc.nextLine();
                    int sidx = findIndex(Principal.studid, Principal.studcount, loginSid);
                    if (sidx == -1) { System.out.println("Student ID not found."); break; }
                    Student student = new Student(Principal.studid[sidx], Principal.studname[sidx], principal);
                    System.out.println("Logged in as Student " + Principal.studname[sidx]);
                    studentMenu(sc, student);
                    break;
                case 8:
                    principal.viewdetails();
                    break;
                case 9: 
                    if (vp == null) { System.out.println("No VP exists. Add VP first."); break; }
                    vpMenu(sc, vp);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
    private static void vpMenu(Scanner sc, VicePrincipal vp) {
        int ch;
        do {
            System.out.println("\n--- VP Menu ---");
            System.out.println("1 - Add HOD");
            System.out.println("2 - Add Faculty");
            System.out.println("3 - Add Student");
            System.out.println("0 - Back");
            System.out.print("Choice: ");
            ch = readInt(sc);
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter HOD ID: ");
                    int hid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter HOD Name: ");
                    String hname = sc.nextLine();
                    System.out.print("Enter HOD Department: ");
                    String hdept = sc.nextLine();
                    vp.addHOD(hid, hname, hdept);
                    break;
                case 2:
                    System.out.print("Enter Faculty ID: ");
                    int fid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter Faculty Name: ");
                    String fname = sc.nextLine();
                    System.out.print("Enter Faculty Department: ");
                    String fdept = sc.nextLine();
                    vp.addFaculty(fid, fname, fdept);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    int sid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Student Department: ");
                    String sdept = sc.nextLine();
                    vp.addStudent(sid, sname, sdept);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (ch != 0);
    }

    private static void hodMenu(Scanner sc, HOD hod) {
        int ch;
        do {
            System.out.println("\n--- HOD Menu (" + hod.getname() + ") ---");
            System.out.println("1 - Add Faculty");
            System.out.println("2 - Add Student (with marks & attendance)");
            System.out.println("3 - Assign work to Faculty");
            System.out.println("4 - View Faculties in my Dept");
            System.out.println("5 - View Students in my Dept");
            System.out.println("0 - Logout HOD");
            System.out.print("Choice: ");
            ch = readInt(sc);
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Faculty ID: ");
                    int fid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter Faculty Name: ");
                    String fname = sc.nextLine();
                    hod.addFaculty(fid, fname);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int sid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Marks (double): ");
                    double marks = readDouble(sc);
                    sc.nextLine();
                    System.out.print("Enter Attendance (int): ");
                    int att = readInt(sc);
                    sc.nextLine();
                    hod.addStudent(sid, sname, marks, att);
                    break;
                case 3:
                    System.out.print("Enter Faculty ID to assign work: ");
                    int fidAssign = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter assignment text: ");
                    String work = sc.nextLine();
                    hod.assignWorkToFaculty(fidAssign, work);
                    break;
                case 4:
                    hod.viewFaculties();
                    break;
                case 5:
                    hod.viewStudents();
                    break;
                case 0:
                    System.out.println("HOD logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (ch != 0);
    }

    private static void facultyMenu(Scanner sc, Faculty f) {
        int ch;
        do {
            System.out.println("\n--- Faculty Menu (" + f.getname() + ") ---");
            System.out.println("1 - Add Student (with marks & attendance)");
            System.out.println("2 - View Assigned Work");
            System.out.println("3 - View Students in my Dept");
            System.out.println("0 - Logout Faculty");
            System.out.print("Choice: ");
            ch = readInt(sc);
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int sid = readInt(sc);
                    sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Marks (double): ");
                    double marks = readDouble(sc);
                    sc.nextLine();
                    System.out.print("Enter Attendance (int): ");
                    int att = readInt(sc);
                    sc.nextLine();
                    f.addStudent(sid, sname, marks, att);
                    break;
                case 2:
                    System.out.println("Assigned work: " + f.getAssignment());
                    break;
                case 3:
                    f.viewStudentsInDept();
                    break;
                case 0:
                    System.out.println("Faculty logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (ch != 0);
    }

    private static void studentMenu(Scanner sc, Student s) {
        int ch;
        do {
            System.out.println("\n--- Student Menu (" + s.getname() + ") ---");
            System.out.println("1 - View My Details");
            System.out.println("0 - Logout Student");
            System.out.print("Choice: ");
            ch = readInt(sc);
            sc.nextLine();
            switch (ch) {
                case 1:
                    s.viewdetails();
                    break;
                case 0:
                    System.out.println("Student logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (ch != 0);
    }
    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1 - Add Vice Principal (Principal)");
        System.out.println("2 - Add HOD (Principal)");
        System.out.println("3 - Login as HOD (perform HOD tasks)");
        System.out.println("4 - Add Faculty (Principal)");
        System.out.println("5 - Login as Faculty (perform Faculty tasks)");
        System.out.println("6 - Add Student (Principal)");
        System.out.println("7 - Login as Student (view own details)");
        System.out.println("8 - View All Records (Principal)");
        System.out.println("9 - VP Actions");
        System.out.println("0 - Exit");
        System.out.print("Enter choice: ");
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        return sc.nextDouble();
    }
    private static int findIndex(int[] arr, int count, int id) {
        for (int i = 0; i < count; i++) if (arr[i] == id) return i;
        return -1;
    }
}
