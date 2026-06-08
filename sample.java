import java.util.Scanner;
public class sample{
    public static void main(String args[]){
         Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        char ch='A';
    for(int i=1;i<=N;i++)
    {
        for(int j=1;j<=N-i;j++){
           System.out.print(" ");
    }
    for(int k=1;k<=(2*i-1)/2+1;k++){
           System.out.print(ch);
           ch++;
    }
    System.out.println(ch);
    for(int l=((2*i-1)/2+2);l<=2*i-1;l++){
      ch--;
      System.out.print(ch);
    }
    System.out.println();
    }
    }
}