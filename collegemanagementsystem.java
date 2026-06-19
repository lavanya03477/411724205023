import java.util.Scanner;

abstract class person{
    private int id;
    private String name;
    private String role;
    private String department; 
    public void setdetails(int id,String name,String role,String department){
        this.id=id;
        this.name=name;
        this.role=role;
        this.department=department;
    }
    public int getid(){
        return id;
    }
    public String getname(){
        return name;
    }
    public String getrole(){
        return role;
    }
    public String getDepartment(){
         return department; 
    }
    abstract void viewdetails();
    abstract void managetasks();
}
class Principal extends person{
    static int vpid[]=new int[50];
    static int hodid[]=new int[50];
    static int faculid[]=new int[50];
    static int studid[]=new int[50];
    static String vpname[]=new String[50];
    static String hodname[]=new String[50];
    static String faculname[]=new String[50];
    static String studname[]=new String[50];
    static int vpcount=0;
    static int hodcount=0;
    static int faculcount=0;
    static int studcount=0;
    static String hoddept[]=new String[100];
    static String faculdept[]=new String[100];
    static String studdept[]=new String[100];
    Principal(int id,String name){
      super(id,name,"Principal");
    }
    void viewDetails() {
    System.out.println("Principal " + getname() + " can view all records:");

    System.out.println("Vice Principals:");
    for (int i = 0; i < vpcount; i++) {
        System.out.println("- " + vpname[i] + " (ID: " + vpid[i] + ")");
    }

    System.out.println("HODs:");
    for (int i = 0; i < hodcount; i++) {
        System.out.println("- " + hodname[i] + " (ID: " + hodid[i] + ")");
    }

    System.out.println("Faculty:");
    for (int i = 0; i < faculcount; i++) {
        System.out.println("- " + faculname[i] + " (ID: " + faculid[i] + ")");
    }

    System.out.println("Students:");
    for (int i = 0; i < studcount; i++) {
        System.out.println("- " + studname[i] + " (ID: " + studid[i] + ")");
    }
}
boolean isUniqueID(int id) {
        for (int i=0;i<vpcount;i++) 
            if (vpid[i]==id) 
                return false;
        for (int i=0;i<hodcount;i++) 
            if (hodid[i]==id) 
                return false;
        for (int i=0;i<faculcount;i++) 
            if (faculid[i]==id) 
                return false;
        for (int i=0;i<studcount;i++)
             if (studid[i]==id) 
                return false;
        return true;
    }
//add methods
 void addVP(int id, String name) {
        if (vpcount >= 1) 
            { System.out.println("Vice Principal already exists!"); 
            return; }
        if (isUniqueID(id)) { 
            vpid[0]=id; 
            vpname[0]=name; 
            vpcount=1;
            System.out.println("VP added: "+name); }
        else {
            System.out.println("Duplicate ID not allowed!");
    }}

    void addHOD(int id, String name, String dept) {
        for (int i=0;i<hodcount;i++)
            if (hoddept[i].equalsIgnoreCase(dept)) {
            System.out.println("HOD already exists for department "+dept);
             return;
        }
        if (isUniqueID(id)) {
            hodid[hodcount]=id;
            hodname[hodcount]=name;
            hoddept[hodcount]=dept; 
            hodcount++;
            System.out.println("HOD added: "+name+" ("+dept+")");
        } else System.out.println("Duplicate ID not allowed!");
    }

    void addFaculty(int id, String name, String dept) {
        if (isUniqueID(id)) {
            faculid[faculcount]=id; 
            faculname[faculcount]=name; 
            faculdept[faculcount]=dept; 
            faculcount++;
            System.out.println("Faculty added: "+name+" ("+dept+")");
        } else System.out.println("Duplicate ID not allowed!");
    }

    void addStudent(int id, String name, String dept) {
        if (isUniqueID(id)) {
            studid[studcount]=id; 
            studname[studcount]=name; 
            studdept[studcount]=dept; 
            studcount++;
            System.out.println("Student added: "+name+" ("+dept+")");
        } else System.out.println("Duplicate ID not allowed!");
    }
    //update methods
    void updateVP(int id, String newName) {
        if (vpcount==1 && vpid[0]==id) { 
            vpname[0]=newName; 
            System.out.println("VP updated."); }
        else System.out.println("VP not found.");
    }
    void updateHOD(int id, String newName) {
        for (int i=0;i<hodcount;i++) 
            if (hodid[i]==id) { 
                hodname[i]=newName; 
                System.out.println("HOD updated."); 
                return; }
        System.out.println("HOD not found.");
    }
    void updateFaculty(int id, String newName) {
        for (int i=0;i<faculcount;i++) 
            if (faculid[i]==id) { 
                faculname[i]=newName; 
                System.out.println("Faculty updated."); 
                return; }
        System.out.println("Faculty not found.");
    }
    void updateStudent(int id, String newName) {
        for (int i=0;i<studcount;i++) 
            if (studid[i]==id) { 
                studname[i]=newName; 
                System.out.println("Student updated."); 
                return; }
        System.out.println("Student not found.");
    }

    // Delete methods
    void deleteVP(int id) {
        if (vpcount==1 && vpid[0]==id) { 
            vpcount=0; 
            System.out.println("VP deleted."); }
        else System.out.println("VP not found.");
    }
    void deleteHOD(int id) {
        for (int i=0;i<hodcount;i++) 
            if (hodid[i]==id) {
            for (int j=i;j<hodcount-1;j++) { 
                hodid[j]=hodid[j+1];
                hodname[j]=hodname[j+1]; 
                hoddept[j]=hoddept[j+1]; }
            hodcount--;
            System.out.println("HOD deleted."); 
             return;
        }
        System.out.println("HOD not found.");
    }
    void deleteFaculty(int id) {
        for (int i=0;i<faculcount;i++) 
            if (faculid[i]==id) {
            for (int j=i;j<faculcount-1;j++) { 
                faculid[j]=faculid[j+1]; 
                faculname[j]=faculname[j+1]; 
                faculdept[j]=faculdept[j+1]; }
            faculcount--; 
            System.out.println("Faculty deleted."); return;
        }
        System.out.println("Faculty not found.");
    }
    void deleteStudent(int id) {
        for (int i=0;i<studcount;i++) if (studid[i]==id) {
            for (int j=i;j<studcount-1;j++) { 
                studid[j]=studid[j+1]; 
                studname[j]=studname[j+1]; 
                studdept[j]=studdept[j+1]; }
            studcount--;
            System.out.println("Student deleted."); 
            return;
        }
        System.out.println("Student not found.");
    }
class VicePrincipal extends person {
    VicePrincipal(int id, String name) { super(id, name, "Vice Principal"); }
    void viewDetails() {
        System.out.println("Vice Principal " + getname() + " can view HOD, Faculty, Students.");

        System.out.println("HODs:");
        for (int i = 0; i < Principal.hodcount; i++) {
            System.out.println("- " + Principal.hodname[i] + " (ID: " + Principal.hodid[i] + ")");
        }

        System.out.println("Faculty:");
        for (int i = 0; i < Principal.faculcount; i++) {
            System.out.println("- " + Principal.faculname[i] + " (ID: " + Principal.faculid[i] + ")");
        }

        System.out.println("Students:");
        for (int i = 0; i < Principal.studcount; i++) {
            System.out.println("- " + Principal.studname[i] + " (ID: " + Principal.studid[i] + ")");
        }
    }

    void manageTasks() {
        System.out.println("Vice Principal can add, update, and delete HOD, Faculty, Students.");
    }

    // ===== Add Methods =====
    void addHOD(int id, String name) {
        if (isUniqueID(id)) {
            Principal.hodid[Principal.hodcount] = id;
            Principal.hodname[Principal.hodcount++] = name;
            System.out.println("HOD added: " + name);
        } else System.out.println("Duplicate ID not allowed!");
    }

    void addFaculty(int id, String name) {
        if (isUniqueID(id)) {
            Principal.faculid[Principal.faculcount] = id;
            Principal.faculname[Principal.faculcount++] = name;
            System.out.println("Faculty added: " + name);
        } else System.out.println("Duplicate ID not allowed!");
    }

    void addStudent(int id, String name) {
        if (isUniqueID(id)) {
            Principal.studid[Principal.studcount] = id;
            Principal.studname[Principal.studcount++] = name;
            System.out.println("Student added: " + name);
        } else System.out.println("Duplicate ID not allowed!");
    }

    // ===== Update Methods =====
    void updateHOD(int id, String newName) {
        for (int i = 0; i < Principal.hodcount; i++) {
            if (Principal.hodid[i] == id) {
                Principal.hodname[i] = newName;
                System.out.println("HOD updated.");
                return;
            }
        }
        System.out.println("HOD not found.");
    }

    void updateFaculty(int id, String newName) {
        for (int i = 0; i < Principal.faculcount; i++) {
            if (Principal.faculid[i] == id) {
                Principal.faculname[i] = newName;
                System.out.println("Faculty updated.");
                return;
            }
        }
        System.out.println("Faculty not found.");
    }

    void updateStudent(int id, String newName) {
        for (int i = 0; i < Principal.studcount; i++) {
            if (Principal.studid[i] == id) {
                Principal.studname[i] = newName;
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // ===== Delete Methods =====
    void deleteHOD(int id) {
        for (int i = 0; i < Principal.hodcount; i++) {
            if (Principal.hodid[i] == id) {
                for (int j = i; j < Principal.hodcount - 1; j++) {
                    Principal.hodid[j] = Principal.hodid[j+1];
                    Principal.hodname[j] = Principal.hodname[j+1];
                }
                Principal.hodcount--;
                System.out.println("HOD deleted.");
                return;
            }
        }
        System.out.println("HOD not found.");
    }

    void deleteFaculty(int id) {
        for (int i = 0; i < Principal.faculcount; i++) {
            if (Principal.faculid[i] == id) {
                for (int j = i; j < Principal.faculcount - 1; j++) {
                    Principal.faculid[j] = Principal.faculid[j+1];
                    Principal.faculname[j] = Principal.faculname[j+1];
                }
                Principal.faculcount--;
                System.out.println("Faculty deleted.");
                return;
            }
        }
        System.out.println("Faculty not found.");
    }

    void deleteStudent(int id) {
        for (int i = 0; i < Principal.studcount; i++) {
            if (Principal.studid[i] == id) {
                for (int j = i; j < Principal.studcount - 1; j++) {
                    Principal.studid[j] = Principal.studid[j+1];
                    Principal.studname[j] = Principal.studname[j+1];
                }
                Principal.studcount--;
                System.out.println("Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Helper: check unique ID
    boolean isUniqueID(int id) {
        for (int i = 0; i < Principal.hodcount; i++) if (Principal.hodid[i] == id) return false;
        for (int i = 0; i < Principal.faculcount; i++) if (Principal.faculid[i] == id) return false;
        for (int i = 0; i < Principal.studcount; i++) if (Principal.studid[i] == id) return false;
        return true;
    }
}

public class collegemanagementsystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Principal principal = new Principal(1, "Dr. Rao");

        while (true) {
            System.out.println("\n===== COLLEGE MANAGEMENT SYSTEM =====");
            System.out.println("Select Role:");
            System.out.println("1. Principal");
            System.out.println("2. Vice Principal");
            System.out.println("3. Exit");
            int roleChoice = sc.nextInt(); sc.nextLine();

            if (roleChoice == 3) {
                System.out.println("Exiting...");
                break;
            }

            switch (roleChoice) {
                case 1: // Principal menu
                    while (true) {
                        System.out.println("\n--- Principal Menu ---");
                        System.out.println("1. Add VP");
                        System.out.println("2. Add HOD");
                        System.out.println("3. Add Faculty");
                        System.out.println("4. Add Student");
                        System.out.println("5. Update VP");
                        System.out.println("6. Update HOD");
                        System.out.println("7. Update Faculty");
                        System.out.println("8. Update Student");
                        System.out.println("9. Delete VP");
                        System.out.println("10. Delete HOD");
                        System.out.println("11. Delete Faculty");
                        System.out.println("12. Delete Student");
                        System.out.println("13. View All Records");
                        System.out.println("14. Back");
                        int choice = sc.nextInt(); sc.nextLine();

                        if (choice == 14) break;

                        switch (choice) {
                            case 1: System.out.print("Enter VP ID: "); int vpid=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter VP Name: "); String vpname=sc.nextLine();
                                    principal.addVP(vpid,vpname); break;
                            case 2: System.out.print("Enter HOD ID: "); int hid=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter HOD Name: "); String hname=sc.nextLine();
                                    System.out.print("Enter Department: "); String hdept=sc.nextLine();
                                    principal.addHOD(hid,hname,hdept); break;
                            case 3: System.out.print("Enter Faculty ID: "); int fid=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter Faculty Name: "); String fname=sc.nextLine();
                                    System.out.print("Enter Department: "); String fdept=sc.nextLine();
                                    principal.addFaculty(fid,fname,fdept); break;
                            case 4: System.out.print("Enter Student ID: "); int sid=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter Student Name: "); String sname=sc.nextLine();
                                    System.out.print("Enter Department: "); String sdept=sc.nextLine();
                                    principal.addStudent(sid,sname,sdept); break;
                            case 5: System.out.print("Enter VP ID to update: "); int upvp=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter new VP Name: "); String newvp=sc.nextLine();
                                    principal.updateVP(upvp,newvp); break;
                            case 6: System.out.print("Enter HOD ID to update: "); int uphod=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter new HOD Name: "); String newhod=sc.nextLine();
                                    principal.updateHOD(uphod,newhod); break;
                            case 7: System.out.print("Enter Faculty ID to update: "); int upfac=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter new Faculty Name: "); String newfac=sc.nextLine();
                                    principal.updateFaculty(upfac,newfac); break;
                            case 8: System.out.print("Enter Student ID to update: "); int upstud=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter new Student Name: "); String newstud=sc.nextLine();
                                    principal.updateStudent(upstud,newstud); break;
                            case 9: System.out.print("Enter VP ID to delete: "); int delvp=sc.nextInt(); sc.nextLine();
                                    principal.deleteVP(delvp); break;
                            case 10: System.out.print("Enter HOD ID to delete: "); int delhod=sc.nextInt(); sc.nextLine();
                                     principal.deleteHOD(delhod); break;
                            case 11: System.out.print("Enter Faculty ID to delete: "); int delfac=sc.nextInt(); sc.nextLine();
                                     principal.deleteFaculty(delfac); break;
                            case 12: System.out.print("Enter Student ID to delete: "); int delstud=sc.nextInt(); sc.nextLine();
                                     principal.deleteStudent(delstud); break;
                            case 13: principal.viewDetails(); break;
                            default: System.out.println("Invalid choice!");
                        }
                    }
                    break;

                case 2: // Vice Principal menu
                    VicePrincipal vp = new VicePrincipal(2,"VP Sharma");
                    while (true) {
                        System.out.println("\n--- Vice Principal Menu ---");
                        System.out.println("1. Add HOD");
                        System.out.println("2. Add Faculty");
                        System.out.println("3. Add Student");
                        System.out.println("4. Update HOD");
                        System.out.println("5. Update Faculty");
                        System.out.println("6. Update Student");
                        System.out.println("7. Delete HOD");
                        System.out.println("8. Delete Faculty");
                        System.out.println("9. Delete Student");
                        System.out.println("10. View Records");
                        System.out.println("11. Back");
                        int choice = sc.nextInt(); sc.nextLine();

                        if (choice == 11) break;

                        switch (choice) {
                            case 1: System.out.print("Enter HOD ID: "); int hid=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter HOD Name: "); String hname=sc.nextLine();
                                    System.out.print("Enter Department: "); String hdept=sc.nextLine();
                                    vp.addHOD(hid,hname,hdept); break;
                            case 2: System.out.print("Enter Faculty ID: "); int fid=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter Faculty Name: "); String fname=sc.nextLine();
                                    System.out.print("Enter Department: "); String fdept=sc.nextLine();
                                    vp.addFaculty(fid,fname,fdept); break;
                            case 3: System.out.print("Enter Student ID: "); int sid=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter Student Name: "); String sname=sc.nextLine();
                                    System.out.print("Enter Department: "); String sdept=sc.nextLine();
                                    vp.addStudent(sid,sname,sdept); break;
                            case 4: System.out.print("Enter HOD ID to update: "); int uphod=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter new HOD Name: "); String newhod=sc.nextLine();
                                    vp.updateHOD(uphod,newhod); break;
                            case 5: System.out.print("Enter Faculty ID to update: "); int upfac=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter new Faculty Name: "); String newfac=sc.nextLine();
                                    vp.updateFaculty(upfac,newfac); break;
                            case 6: System.out.print("Enter Student ID to update: "); int upstud=sc.nextInt(); sc.nextLine();
                                    System.out.print("Enter new Student Name: "); String newstud=sc.nextLine();
                                    vp.updateStudent(upstud,newstud); break;
                            case 7: System.out.print("Enter HOD ID to delete: "); int delhod=sc.nextInt(); sc.nextLine();
                                    vp.deleteHOD(delhod); break;
                            case 8: System.out.print("Enter Faculty ID to delete: "); int delfac=sc.nextInt(); sc.nextLine();
                                    vp.deleteFaculty(delfac); break;
                            case 9: System.out.print("Enter Student ID to delete: "); int delstud=sc.nextInt(); sc.nextLine();
                                    vp.deleteStudent(delstud); break;
                            case 10: vp.viewDetails(); break;
                            default: System.out.println("Invalid choice!");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid role!");
            }
        }
    }
}
}

