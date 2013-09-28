import com.osomac.threading.CTThreadPool;

public class SquareRooter {
	
	public static void main(String[] args) {
		CTThreadPool pool = new CTThreadPool(10);
		
		for(int i = 0; i < 1000; ++i) {
			SqrtJob job = new SqrtJob(Math.random() * 1000);
			pool.addJob(job);
		}

	}

}
