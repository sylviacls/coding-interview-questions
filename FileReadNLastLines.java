import jdk.javadoc.internal.doclets.formats.html.SplitIndexWriter;

/**
 * Given some text lines in one string, each line is separated by ‘\n’ character. 
 * Print the last N lines. If the number of lines is less than N, then print all lines.
 */
public class FileReadNLastLines {
    
    public static void readNLastLines(String input, int lines) {
        String[] slipted = input.split("\n");

        if(slipted.length <1) {
            System.out.println("ERROR: string doesn't " +  
            "contain '\\n' character");
            return;
        }

        if(slipted.length <= lines) {
            for (int i = 0; i < slipted.length; i++) {
                System.out.println(slipted[i]);      
            }
        } else{
            for (int i = (slipted.length - lines); i < slipted.length; i++) {
                System.out.println(slipted[i]);
            }
        }
    }

    public static void main(String[] args) {
        String s1 = "str1\nstr2\nstr3\nstr4\nstr5\nstr6\nstr7" +  
        "\nstr8\nstr9\nstr10\nstr11\nstr12\nstr13" +  
        "\nstr14\nstr15\nstr16\nstr17\nstr18\nstr19" +  
        "\nstr20\nstr21\nstr22\nstr23\nstr24\nstr25"; 
        readNLastLines(s1, 30);
        System.out.println();
        readNLastLines(s1, 3);
    }
}
