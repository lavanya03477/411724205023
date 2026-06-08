import java.util.*;
import java.lang.*;
public class Array{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=10;
        int mark[]=new int[10];
        
        for(int i=0;i<n;i++){
           mark[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            System.out.print(mark[i]);
        }
    }
}