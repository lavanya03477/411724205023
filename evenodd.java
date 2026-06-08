public class evenodd {
    public static void main(String[] args) {
        int arr[]={10,25,13,14,19,20};
        int odd=0;
        int even=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]%2==0){
                even++;
            }
            else{
                odd++;
            }
    }
    System.out.println(even);
    System.out.println(odd);
}
}
