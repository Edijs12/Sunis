package suns;

import java.time.Year;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class Saimnieks {
	
	static String virknesParbaude(String zinojums, String noklusejums) {
		String virkne;
		do {
			virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
			if(virkne == null)
				return null;
			
			virkne = virkne.trim();
		}while(!Pattern.matches("^[\\p{L} ]+$", virkne));
		return virkne;
	}
	static int skaitlaParbaude(String zinojums, int min, int max) {
		String ievade;
		int skaitlis;
		
		while(true) {
			ievade = JOptionPane.showInputDialog(zinojums, min);
			if(ievade == null)
				return -1;
			
			try {
				skaitlis = Integer.parseInt(ievade);
				if(skaitlis < min || skaitlis > max) {
					JOptionPane.showMessageDialog(null, "Norādītais skaitlis ir ārpus pieļaujamā intervāla",
							"Brīdinājums", JOptionPane.WARNING_MESSAGE);
					continue;
				}
				
				return skaitlis;
				
				
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Netika ievadīts vesels skaitlis!", 
						"Kļūda", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	public static void main(String[] args) {
		String izvelne;
		Suns suns = null;
		String[] darbiba = {"Izveidot suni", "izsaukt metodi", "Aizvērt"};
		
		do {
			izvelne = (String) JOptionPane.showInputDialog(null,
					"Izvēlies darbību", "darbību Izvēlne",
					JOptionPane.QUESTION_MESSAGE,
					null, darbiba, darbiba[0]);
			
			if(izvelne == null)
				izvelne = "Aizvērt";
			
			switch(izvelne) {
			case "Izveidot suni":
			String sunaVards, skirne, spalvasKrasa, saimnieks;
			int dzGads = 0;
			boolean siksnina;	
			
			sunaVards = 
					virknesParbaude("Ievadi kaķa vārdu", "Molija");
			if(sunaVards == null)
				break;
			
			skirne =
					virknesParbaude("Norādi kaķa šķirni", "Mopsis");
			if(skirne == null)
				break;
			
			spalvasKrasa = 
					virknesParbaude("Kāda ir kaķa kažoka krāsa?", "Blonda");
			if(spalvasKrasa == null)
				break;
			
			saimnieks = 
					virknesParbaude("Kā sauc " + sunaVards +" saimnieku", "Sindija");
			if(saimnieks == null)
				break;
			
			dzGads =
					skaitlaParbaude("Norādi suņa dzimšanas gadu",
					(Year.now().getValue())- 19, (Year.now().getValue()));
			if(dzGads == -1)
				break;
			
			int poga = JOptionPane.showConfirmDialog(null,
					"Vai sunim ir uzlikta siksniņa?", "Siksniņa",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			
			if(poga == -1)
				break;
			
			siksnina = (poga == 0) ? true : false;
			
			suns  = new Suns(sunaVards, skirne,
					spalvasKrasa, saimnieks,
					dzGads, siksnina);
			break;

			case "izsaukt metodi":
				if(suns != null) {
					String[] metozuSaraksts = {"Mainit suņa atribūtus", "Nolasīt atribūtus",
							"Pabarot", "iet pastaigā", "Palielināt vecumu", "Apskatīt vecumu", "Riet"};
					
					String metode = (String) JOptionPane.showInputDialog(null,
							"Izvēlies metodi","Metodes izvēle",
							JOptionPane.QUESTION_MESSAGE,
							null, metozuSaraksts, metozuSaraksts[0]);
					
					if(metode == null)
						break;
					
					
					
					switch(metode) {
					case "Mainit suņa atribūtus":
						if(suns != null) {
						String[] atributuIzvele = {"Mainit vārdu", "Mainīt šķirni", "Mainīt spalvas krāsu", "Mainīt saimnieku", "Mainīt siksniņas statusu"};
						
						String atributasMaina = (String) JOptionPane.showInputDialog(null,
								"Izvēlies kuru atribūtu mainīt", "Atribūtu maiņa",
								JOptionPane.QUESTION_MESSAGE,
								null, atributuIzvele, atributuIzvele[0]);
						
						if(atributasMaina != null)
							switch(atributasMaina) {
							case "Mainit vārdu":
								String jaunsVards = virknesParbaude("Ievadi jauno vārdu", suns.vards);
			                    if (jaunsVards != null) {
			                        suns.mainitVardu(jaunsVards);
			                    }
			                    break;
			                    
							case "Mainīt šķirni":
								String jaunsSkirne = virknesParbaude("Ievadi jauno skirni", suns.skirne);
								if(jaunsSkirne != null) {
									suns.mainitSkirni(jaunsSkirne);
								}
								break;
								
							case "Mainīt spalvas krāsu":
								String jaunsSpalvasKrasa = virknesParbaude("Ievadi jauno skirni", suns.spalvasKrasa);
								if(jaunsSpalvasKrasa != null) {
									suns.mainitSpalvasKrasu(jaunsSpalvasKrasa);
								}
								break;
								
							case "Mainīt saimnieku":
								String jaunsSaimnieks = virknesParbaude("Ievadi jauno skirni", suns.saimnieks);
								if(jaunsSaimnieks != null) {
									suns.mainitSpalvasKrasu(jaunsSaimnieks);
								}
								break;
			                    
							case "Mainīt siksniņas statusu":
								suns.mainitSiksnu();
								break;
							}
						
						
						break;
						}
						
					case "Nolasīt atribūtus":
						JOptionPane.showMessageDialog(null, suns.nolasitAtributus());
						break;
						
					case "Pabarot":
						String atbilde = suns.pabarot(virknesParbaude(
								"Ar ko pabarosim suni?", "Desu"));
						JOptionPane.showMessageDialog(null, "Sunis atgriež "
								+ atbilde, "Informācija", JOptionPane.INFORMATION_MESSAGE);
						break;
					
					case "iet pastaigā":
						suns.vestPastaiga();
						break;
						
					case "Palielināt vecumu":
						suns.palielinatVecumu();
						break;
						
					case "Apskatīt vecumu":
						suns.nolaitVecumu();
						break;
						
					case "Riet":
					    suns.riet();
					    break;
					    
					
						
					}
			
			}else
			
					JOptionPane.showMessageDialog(null, 
						"Vispirms izveido suni!", 
						"Brīdinājums",JOptionPane.WARNING_MESSAGE);
				break;
				
			case "Aizvērt":
				JOptionPane.showMessageDialog(null, "Programma apturēta!", "Informācija", JOptionPane.INFORMATION_MESSAGE);
				break;
			
		}
			
		

	}while(!izvelne.equals("Aizvērt"));

  }
}
