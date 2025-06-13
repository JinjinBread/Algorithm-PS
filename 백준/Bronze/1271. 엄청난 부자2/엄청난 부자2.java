import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        BigInteger n = new BigInteger(st.nextToken()); // 돈
        BigInteger m = new BigInteger(st.nextToken()); // 인원

        BigInteger quotient = n.divide(m);
        BigInteger remainder = n.mod(m);

        System.out.println(quotient);
        System.out.println(remainder);
    }
    
}
