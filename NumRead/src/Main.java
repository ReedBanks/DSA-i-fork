import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

//:TODO scenario1
//create hashmap<number_str,occurances_int
//increment occurances using counter as they appear
//check if number already exist as key and increment its counter

//write outcome to file

//function to count occurances on lines

public class Main {
    public static void main(String[] args) {

        String dtpath="C:\\Users\\rexx\\Desktop\\PROJECT_FILES\\java\\NumRead\\src\\data.txt";
        String rspath="C:\\Users\\rexx\\Desktop\\PROJECT_FILES\\java\\NumRead\\src\\results.txt";

        try {
            FileReader _reader=new FileReader(dtpath);
            FileWriter _writer=new FileWriter(rspath);
            HashMap<Character,Integer> dict=new HashMap<>();
            HashMap<Character, ArrayList<Integer>> line_occ=new HashMap<>(); //lines the keys occur on
            int counter=1; //count number of times num occurs
            int  line_count=1;//count lines the num occurs on
           // Integer[] line=new Integer[10];


            int data=_reader.read();
            //read data

           while(data !=-1 ){
                char current_char=(char) data;
                   if (dict.containsKey(current_char)) {
                        int val = dict.get(current_char);
                        dict.remove(current_char);
                        val++;
                        dict.put(current_char, val);
//line counts

                        line_occ.putIfAbsent(current_char,new ArrayList<>());

                       if (!line_occ.get(current_char).contains(line_count)) {
                           line_occ.get(current_char).add(line_count);
                       }
                    } else {
                        dict.put((char) data, counter);

                    }
                    //increase counter at the end of each
                    if(current_char == '\n'){
                        line_count++;
                    }
                    data = _reader.read();

            }
           //remove space keys
//          dict.remove('\n');
           dict.remove(' ');
            line_occ.remove(' ');


//return all keys && values
           _writer.write("{ ");
      for(Character key : dict.keySet()){
            Integer value=dict.get(key);
            System.out.print(key + ":" +value+ " ");
           if(key !='\n' && key !=' ') _writer.write(key + ":" +value+ ",");
        }
            _writer.write("}\n \n");

//return keys with their line occurance
            for(Character key : line_occ.keySet()){
                System.out.println(key+": "+line_occ.get(key));
                if(key !='\n' && key !=' ')_writer.write(key + ":" +line_occ.get(key)+ "\n");
            }

            _reader.close();_writer.close();

        }
        catch (IOException e){
            System.out.println(" io error");
        }

    }
}