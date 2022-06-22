public class HexadecimalConverter {

    public static void main(String[] args) {
        String hex = args[0];
        int dec = Integer.parseInt(hex, 16);
        System.out.printf("Decimal of %s is: %d", hex, dec);

    }    

}