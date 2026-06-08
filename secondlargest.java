public class secondlargest {
    public static void main(String[] args){
        int arr[]={10,14,24,78,98};
        int n=arr.length;
        int largest=arr[0];
        int secondlargest=arr[0];
        for(int i=0;i<n;i++){
            if(arr[i]>largest){
                secondlargest=largest;
                largest=arr[i];
            }
            else if(arr[i]>secondlargest && arr[i]!=largest){
                secondlargest=arr[i];
            }
        }
        System.out.println(secondlargest);
        }
    }
    
