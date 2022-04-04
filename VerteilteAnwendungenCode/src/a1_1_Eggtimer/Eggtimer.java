package a1_1_Eggtimer;

public class Eggtimer extends Thread {
	public int seconds;
	public String name;

	public Eggtimer(int seconds, String name) {
		this.seconds = seconds;
		this.name = name;
	}

	public void run() {
		sleeping(seconds, name);
	}

	public static synchronized void sleeping(int seconds, String name) {
		try {
			for (int i = seconds; i >= 0; i--) {
				if (i == 0) {
					System.out.println(name + " ist fertig!");
				} else {
					System.out.println("Es verbleiben noch " + i + " Sekunden!");
					Thread.sleep(1000 * seconds);
				}
			}
		} catch (InterruptedException t) {
			System.out.print("Fehler");
		}

	}

	public static void main(String[] args) {
		eggtimer(6, "A");
		eggtimer(8, "C");
		eggtimer(4, "B");
	}

	public static void eggtimer(int seconds, String name) {
		Eggtimer eggtimer = new Eggtimer(seconds, name);
		eggtimer.start();
	}
}