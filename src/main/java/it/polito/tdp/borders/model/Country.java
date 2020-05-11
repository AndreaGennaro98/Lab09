package it.polito.tdp.borders.model;

public class Country {
	
	int codice;
	String nomeAbb;
	String nome;
	int numconfini;
	
	public Country(int codice, String nomeAbb, String nome, int num) {
		super();
		this.codice = codice;
		this.nomeAbb = nomeAbb;
		this.nome = nome;
		this.numconfini=num;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getNomeAbb() {
		return nomeAbb;
	}

	public void setNomeAbb(String nomeAbb) {
		this.nomeAbb = nomeAbb;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumconfini() {
		return numconfini;
	}

	public void setNumconfini(int numconfini) {
		this.numconfini = numconfini;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (codice != other.codice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Country [codice=%s, nomeAbb=%s, nome=%s, numconfini=%s]", codice, nomeAbb, nome,
				numconfini);
	}
	
	
	
	

}
