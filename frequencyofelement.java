public class frequencyofelement {
    public static void main(String[] args) {
        int arr[]={10,14,20,25,39,25};
        int n=arr.length;
        int e=25;
        int c=0;
        for(int i=0;i<n;i++){
           if(arr[i]==e){
            c++;
           }
        }
        System.out.println(c);
        
    }
}