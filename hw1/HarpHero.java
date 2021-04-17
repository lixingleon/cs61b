/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.HarpString;

public class HarpHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        HarpString[] strings = new HarpString[37];
        for (int i =0; i<37; i++){
            strings[i] = new HarpString(440* Math.pow(2,(i-24)/12));
        }
        HarpString string = strings[0];
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index!=-1){
                    string = strings[index];
                    string.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = string.sample() ;

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            string.tic();

        }
    }
}

