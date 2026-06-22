public class array2 {
    public static void main(String[] args) {
        int a[]={1,2,0,3,0,0,4,0};
        int j=0;
        int temp;
        for(int i=0;i<a.length;i++){
            if(a[i]!=0){
                temp=a[i];
                a[i]=a[j];
                a[j]=temp;
                j++;           
        }
}
for(int i:a){
    System.out.print(i+" ");
}
        // for(int i:a){
        //     if(i!=0){
        //         System.err.print(i+" ");
        //     }
        // }
        // for(int i:a){
        //     if(i==0){
        //       System.err.print(i+" ");
        //     }
        // }


    }
}
