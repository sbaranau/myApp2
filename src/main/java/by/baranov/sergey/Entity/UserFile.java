package by.baranov.sergey.Entity;

import javax.persistence.*;

/**
 *TODO
 */
@Entity
@Table(name = "userfile")
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfile")
    private Long idfile;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filetype")
    private String filetype;

    @Column(name = "file")
    private byte[] file;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "adv_id")
    private Adv adv;

    public Long getIdfile() {
        return idfile;
    }

    public void setIdfile(Long idfile) {
        this.idfile = idfile;
    }

    public Adv getAdv() {
        return adv;
    }

    public void setAdv(Adv adv) {
        this.adv = adv;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public UserFile() {
    }
}
