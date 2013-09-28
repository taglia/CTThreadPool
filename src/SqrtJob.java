import java.util.logging.Logger;

import com.osomac.threading.CTThreadPool;


public class SqrtJob implements Runnable {
		private double sqrtee;
		private double sqrted;
		
		private static Logger logger = Logger.getLogger(CTThreadPool.class.getName());
		
		public SqrtJob(double number) {
			sqrtee = number;
		}

		public double getSqrtee() {
			return sqrtee;
		}

		public void setSqrtee(double sqrtee) {
			this.sqrtee = sqrtee;
		}

		public double getSqrted() {
			return sqrted;
		}

		public void run() {
			sqrted = Math.sqrt(sqrtee);
			logger.info("sqrt(" + sqrtee + ") = " + sqrted);
		}
		
	}