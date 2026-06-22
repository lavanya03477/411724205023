public class array3 {
    public static void main(String[] args) {
        int arr[]={1,2,3,5,6,7,8};
        for(int i=1;i<arr.length-1;i++){
            if(arr[i]!=arr[i-1]+1){
            System.out.print("Missing number:"+(arr[i-1]+1));
            break;
}
}
    }
}