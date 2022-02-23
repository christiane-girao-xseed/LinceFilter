import java.time.Instant;

public class T1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long X =Instant.now().getEpochSecond()- Long.parseLong("1645636189");
		if (X<10)
		{
			System.out.print("a");
		}
			}

}
