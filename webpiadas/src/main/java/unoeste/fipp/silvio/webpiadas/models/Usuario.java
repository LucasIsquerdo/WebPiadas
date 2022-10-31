package unoeste.fipp.silvio.webpiadas.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long uid;   
    
    @Column(name="email")
    private String uemail;

    @Column(name="nome")
    private String unome;

    @Column(name="senha")
    private String usenha;

    @Column(name="permissao")
    private int upermissao;

    public Usuario() {
        this("", "", "", 0);
    }

    public String getNome() {
        return unome;
    }

    public void setNome(String nome) {
        this.unome = nome;
    }

    public String getEmail() {
        return uemail;
    }

    public void setEmail(String email) {
        this.uemail = email;
    }

    public String getSenha() {
        return usenha;
    }

    public void setSenha(String senha) {
        this.usenha = senha;
    }

    public int getPermissao() {
        return upermissao;
    }

    public void setPermissao(int permissao) {
        this.upermissao = permissao;
    }

    public Usuario(String nome, String email, String senha, int permissao) {
        this.unome = nome;
        this.uemail = email;
        this.usenha = senha;
        this.upermissao = 0;
    }
    
    

}
