// Nistor Marian-Sergiu, 2B6

public class Exercise2 {
	public static void Run() {
		String[] languages = { "C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java" };
		
		int n = (int) (Math.random() * 1_000_000);
		n = n * 3;
		n = n + 0b10101;
		n = n + 0xFF;
		n = n * 6;
		
		int cifSum, cn;
		while (n > 9) {
			cifSum = 0;
			cn = n;
			while (cn != 0) {
				cifSum += cn % 10;
				cn /= 10;
			}
			
			n = cifSum;
		}
		
		System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
	}
}
