import java.util.HashMap;

public class FileStatistics {

    /**
     * "my.song.mp3 11b
        greatSong.flac 1000b
        not3.txt 5b
        video.mp4 200b
        game.exe 100b
        mov!e.mkv 10000b"
     */

     public enum FileType {
         MUSIC("music", new String[]{".mp3", ".aac", ".flac"}),
         MOVIE("movie", new String[]{".mp4",".avi",".mkv"}),
         IMAGE("image", new String[]{".jpg",".bmp",".gif"});

         private final String name;
         private final String[] extensions;
         private FileType(String name, String[] extensions) {
            this.name = name;
            this.extensions = extensions;
         }

         public String getName() {
             return name;
         }

         public String[] getExtensions() {
             return extensions;
         }
         
     }

     public static String fileStatistics(String input) {

         input = input.toLowerCase();
         FileType[] fts = FileType.values();
         HashMap<String,Integer> map = new HashMap<String,Integer>();
         map.put(FileType.IMAGE.name, 0);
         map.put(FileType.MUSIC.name, 0);
         map.put(FileType.MOVIE.name, 0);
         map.put("other", 0);

         String[] lines = input.split("\n");
         for (String line : lines) {
            String fileName = line.split(" ")[0];
            int size = Integer.parseInt(line.split(" ")[1].replace("b", ""));
            boolean other = true;
            outerloop:
            for (FileType ft : fts) {
                String[] extensions = ft.getExtensions();
                for (String extension : extensions) {
                    if(fileName.contains(extension)) {
                       int storedSize = map.get(ft.name);
                       storedSize += size;
                       map.put(ft.name, storedSize);
                       other = false;
                       break outerloop;
                    } else {
                        other = true;
                    }
                }
            }
            if(other) {
                int storedSizeOther = map.get("other");
                storedSizeOther += size;
                map.put("other", storedSizeOther);
            }
         }

        StringBuilder result = new StringBuilder();
        result.append(FileType.MUSIC.name + " " + map.get(FileType.MUSIC.name) +"b" + "\n");
        result.append(FileType.IMAGE.name + " " + map.get(FileType.IMAGE.name) +"b" + "\n");
        result.append(FileType.MOVIE.name + " " + map.get(FileType.MOVIE.name) +"b" + "\n");
        result.append("other" + " " + map.get("other") +"b" + "\n");
         return result.toString();
     }

     public static void main(String[] args) {
         StringBuilder file = new StringBuilder();
         file.append("my.song.mp3 11b");
         file.append("\n");
         file.append("greatSong.flac 1000b");
         file.append("\n");
         file.append("not3.txt 5b");
         file.append("\n");
         file.append("video.mp4 200b");
         file.append("\n");
         file.append("game.exe 100b");
         file.append("\n");
         file.append("mov!e.mkv 10000b");
         System.out.println(fileStatistics(file.toString()));
     }

}