//input=[1,2,3,4,5,6,7] output=[2,3,4,5,6,7,1]
//input=[1,2,3,4,5,6,7] output=[4,5,6,7,1,2,3],int k=3;
public class array1{
    public static void main(String[] args) {
        int arr[]={1,2,3,0,0,4,5,0,6,7};
        int k=3;
        int n=arr.length;
        for(int j=0;j<k;j++){
            int temp=arr[0];
        for(int i=0;i<n-1;i++){
            arr[i]=arr[i+1];
        }
        arr[n-1]=temp;
    }
        for(int i=0;i<n;i++){
            if(arr[i]==0){

}
else{
    System.out.print(arr[i]);
}
    }
}
}