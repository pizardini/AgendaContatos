package model;

import enums.TipoNome;

public class Nome {
    
    private String nome;
    private String sobreNome;
    private TipoNome tipoNome;

    

    public Nome(String nome, String sobreNome, TipoNome tipoNome) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.tipoNome = tipoNome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobreNome() {
        return sobreNome;
    }
    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
    public TipoNome getTipoNome() {
        return tipoNome;
    }
    public void setTipoNome(TipoNome tipoNome) {
        this.tipoNome = tipoNome;
    }

    @Override
    public String toString() {
        return "Nome [nome=" + nome + ", sobreNome=" + sobreNome + ", tipoNome=" + tipoNome + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((sobreNome == null) ? 0 : sobreNome.hashCode());
        result = prime * result + ((tipoNome == null) ? 0 : tipoNome.hashCode());
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
        Nome other = (Nome) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (sobreNome == null) {
            if (other.sobreNome != null)
                return false;
        } else if (!sobreNome.equals(other.sobreNome))
            return false;
        if (tipoNome != other.tipoNome)
            return false;
        return true;

    }

}
