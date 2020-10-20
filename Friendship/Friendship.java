import java.io.*;  
import java.util.*;

public class Friendship {
    
    private ArrayList<String> readData(String filename) throws FileNotFoundException, IOException {
        ArrayList <String> information = new ArrayList();

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line;
        //read information begin
        while ((line = br.readLine()) != null) {
            information.add(line);
        }
            
        return information; 
    }

    private void writeData(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        ArrayList <String> data = this.readData("Friendship2.txt"); 
        StringTokenizer st = new StringTokenizer(data.get(0), ",");  

        bw.write( "*Vertices " + (data.size()-1) + "\n" ); 

        int idx = 1; 
        while (st.hasMoreTokens()) {  
            bw.write( idx + " " + st.nextToken() + "\n" ); 
            idx++; 
        }  

        bw.write( "*Edges \n" ); 

        ArrayList<String> arr = new ArrayList<String>(); 
        for( int i = 1; i < data.size() ; i++ ) {
            StringTokenizer st2 = new StringTokenizer( data.get(i), ",");  

            int j = 0; 
            while ( st2.hasMoreTokens() ) {  
                if( st2.nextToken().equals("1" ) ) {
                    if( arr.contains(j + " " + i)) {
                        //do nothing
                    }
                    else {
                        arr.add(i + " " + j); 
                        bw.write( i + " " + j + "\n")  ; 
                    }
                } 
                j++; 
            }  
        }

        //write information end
        bw.close();
    }
    
  
    public static void main(String[] args) throws IOException
    {
        Friendship f = new Friendship();
        f.writeData("VerticesEdges.txt"); 
     
    }
}