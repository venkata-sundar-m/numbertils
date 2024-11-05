package com.maligai.numbertils.numword;

public class WesternNumWord implements NumWord{
    public static String[] data = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine","ten",
    "eleven","twelve","thir","four","fif","six","seven","eigh","nin","twen","hundred"," thousand"," million"," billion"," trillion"};
    
    public String ntow(Double number) throws RuntimeException {
        StringBuilder wholeWord = new StringBuilder();
        StringBuilder partWord = new StringBuilder();
        int whole = number.intValue();
        int tenpowers = 21;
        while(whole > 0) {
            int part = whole%1000;
            int twoDigit = part%100;
            int trdDigit = (part - twoDigit)/100;
            if(trdDigit >0) partWord.append(" ").append(data[trdDigit]).append(" hundred ");
            if(twoDigit != 0) {
                if(trdDigit >0 ) partWord.append("and ");
                int tDigit =  (twoDigit-twoDigit%10);
                partWord.append(tDigit == 20 ? data[tDigit]: tDigit > 20? data[(tDigit/10)+10]: data[twoDigit]);
                partWord.append( (twoDigit >12 && twoDigit<20) ? "teen ": twoDigit>19?"ty ":"");
                partWord.append( twoDigit>20 && part%10 !=0 ?  data[part%10]:"" );
            }
            if(tenpowers>21 && part != 0) {    partWord.append(data[tenpowers]);}
            wholeWord.insert(0, partWord);
            partWord.delete(0,partWord.length());
            whole = whole/1000;
            tenpowers++;
        }
        return wholeWord.toString().trim();
    }

    public static void main(String... arg){
        NumWord nw = new WesternNumWord();
        System.out.println(nw.ntow(221112221.0));
        //System.out.println(ntow(1000001.0));
        long start = System.currentTimeMillis();
        //    for(double d =50000; d < 59000; d++){
        //         System.out.println(ntow(d, NUM_SYSTEM.WESTERN));
        //     }
        System.out.println("done : " + ( System.currentTimeMillis() - start));
    }

}
