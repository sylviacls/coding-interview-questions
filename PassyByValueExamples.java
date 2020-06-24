public class PassyByValueExamples {

    public static void main(String[] args) {
        /**
         * Wrapper classes are immutable: String, Integer, Float, etc..
         */
        String name = "Syl";
        changeToHomer(name);
        System.out.println(name);
        Integer i = 100;
        changeInteger(i);
        System.out.println(i);

        /**
         * Most objects are mutable: StringBuilder
         */
        StringBuilder sb = new StringBuilder("Sylvia");
        addLastName(sb);
        System.out.println(sb);

        StringBuilder warriorProfession = new StringBuilder("Dragon ");
        String warriorWeapon = "Sword ";
        changeWarriorClass(warriorProfession, warriorWeapon);
    
        System.out.println("Warrior=" + warriorProfession + " Weapon=" + warriorWeapon);
    }

    static void addLastName(StringBuilder sb) {
        sb.append(" Campos");
    }

    static void changeToHomer(String name) {
        name = "Homer";
    }

    static void changeInteger(Integer i) {
        i = 10000;
    }

    static void changeWarriorClass(StringBuilder warriorProfession, String weapon) {
        warriorProfession.append("Knight");
        weapon = "Dragon " + weapon;
    
        weapon = null;
        warriorProfession = null;
      
    
    }
}