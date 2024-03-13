package pocCICD.web.filme;

public class Filme {
	private String filmeNome = null;
	private String filmeAno = null;
	private String diretorNome = null;
	private String diretorNascto = null;
	
	public Filme (String filmeNome, String filmeAno, String diretorNome, String diretorNascto) {
		this.filmeAno = filmeAno ;
		this.filmeNome = filmeNome ;
		this.diretorNascto = diretorNascto ;
		this.diretorNome = diretorNome ;
	}
	
	public String getfilmeNome() {
		return filmeNome;
	}
	public String getDiretorNome() {
		return diretorNome;
	}
	public String getFilmeAno() {
		return filmeAno;
	}
	public String getDiretorNascto() {
		return diretorNascto;
	}

}
