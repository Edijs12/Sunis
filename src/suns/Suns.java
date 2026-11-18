package suns;

import java.io.File;
import java.time.Year;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Suns {
	String vards, skirne, spalvasKrasa, saimnieks;
	int vecums, izsalkums;
	boolean siksnina;
	Random rand = new Random();
	
	public Suns (String vards, String skirne,
				String spalvasKrasa, String saimnieks,
				int dzGads, boolean siksnina) {
		
		this.vards = vards;
		this.skirne = skirne;
		this.spalvasKrasa = spalvasKrasa;
		this.saimnieks = saimnieks;
		vecums = Year.now().getValue() - dzGads;
		izsalkums = kungis();
		this.siksnina = siksnina;
	}
	
	public int kungis() {
		return rand.nextInt(5)+1;
	}
	
	String nolasitAtributus() {
		return vards + " ir " + vecums + " gadus vecs " + skirne + " Skirnes sunis ar " + spalvasKrasa + " krāsas kažoku.\n"
				+ "sunim " + ((siksnina)? "ir" : "nav") + " siksniņa.\n"
				+ "Saimnieka vārds: "+ saimnieks;
	}
	
	public void mainitVardu (String jaunsVards) {
		this.vards = jaunsVards;
	}
	public void mainitSkirni (String jaunsSkirne) {
		this.skirne = jaunsSkirne;
	}
	public void mainitSpalvasKrasu (String jaunsSpalvasKrasa) {
		this.spalvasKrasa = jaunsSpalvasKrasa;
	}
	public void mainitSaimnieku (String jaunsSaimnieks) {
		this.saimnieks = jaunsSaimnieks;
	}
	void mainitVecumu () {
		if(vecums > 0) {
			vecums++;
		JOptionPane.showMessageDialog(null, vards + 
				" vecums palielināts par vienu gadu!",
				"Svinības", JOptionPane.INFORMATION_MESSAGE);
	}else 
		JOptionPane.showMessageDialog(null, 
				"Sasniegts maksimālais vecums!", "Brīdinājums",
				JOptionPane.WARNING_MESSAGE);
	}
	
	void nolaitVecumu() {
		JOptionPane.showMessageDialog(null, vards + " ir " + vecums
				+ " gadus vecs.", "Informācija", JOptionPane.INFORMATION_MESSAGE);
	}
	
	String pabarot(String ediens) {
		if (izsalkums > 0) {
			JOptionPane.showMessageDialog(null, "Namm ņamm ņammmm, man garšo "+ ediens,
					"Informācija", JOptionPane.INFORMATION_MESSAGE);
			ediens = "Tukša bļodiņa";
			izsalkums--;
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Esmu pārēdies un " + ediens + " nevēlos!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
		izsalkums = kungis();
		
		}
		return ediens;
	}
	  	void vestPastaiga(){
		if(siksnina == true) {
			JOptionPane.showMessageDialog(null,
					vards+ " ir priecīgs un iet pastaigā",
					"Paziņojums",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,
					"Vispirms vajag uzlikt siksniņu "+vards,
					"Paziņojums",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	  	void mainitSiksnu() {
	  	    siksnina = !siksnina;
	  	    JOptionPane.showMessageDialog(null,
	  	    		vards + " siksniņas statuss mainīts. Tagad siksniņa ir: " + (siksnina ? "uzlikta" : "noņemta"),
	  	            "Informācija",
	  	            JOptionPane.INFORMATION_MESSAGE);
	  	}	
	 void riet() {
		 try {
			 File audioFile = new File("riet.wav");
		 
		 AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
         Clip clip = AudioSystem.getClip();
         clip.open(audioStream);
         clip.start();
       
         JOptionPane.showMessageDialog(null,
        		 vards + " rej: Vaf vaf!", "Suns rej",
        		 JOptionPane.INFORMATION_MESSAGE);
         
     } catch (Exception IO) {
     
         System.out.println("Neizdevās atskaņot skaņu: " + IO.getMessage());
         JOptionPane.showMessageDialog(null, vards + " rej (bez skaņas faila): Vaf vaf!");
     }
  }
}

