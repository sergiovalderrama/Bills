package sergio.projects;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "", schema = "JAVAUSER", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bills.findAll", query = "SELECT b FROM Bills b"),
    @NamedQuery(name = "Bills.findByName", query = "SELECT b FROM Bills b WHERE b.name = :name"),
    @NamedQuery(name = "Bills.findByAmount", query = "SELECT b FROM Bills b WHERE b.amount = :amount"),
    @NamedQuery(name = "Bills.findById", query = "SELECT b FROM Bills b WHERE b.id = :id")})
    
public class Bills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(nullable = false, length = 40)
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 6, scale = 2)
    private BigDecimal amount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    public Bills() {
    }

    public Bills(Integer id) {
        this.id = id;
    }
    
    public Bills(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    Bills(Integer id, String name, BigDecimal amount) {
        this.id= id;
        this.name = name;
        this.amount = amount;
    }
    Bills(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAll(String name, BigDecimal amount){
        this.name = name;
        this.amount = amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bills)) {
            return false;
        }
        Bills other = (Bills) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sergio.projects.Bills[ id=" + id + " ]";
    }
    
}
