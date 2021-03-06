package com.eunoia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
 
/**
 * Base entity
 *
 * @author Dileep
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    protected Long id;
 
    @Column(name = "version")
    @Version
    private Long version;
    
    
    @Temporal( TemporalType.TIMESTAMP )
    @CreationTimestamp
    @Column(name = "created_on")
    private Date createdOn;
    
    
    @Temporal( TemporalType.TIMESTAMP )
    @CreationTimestamp
    @Column(name = "modified_on")
    private Date modifiedOn;
 
    public Long getId() {
        return id;
    }
 
    public Long getVersion() {
        return version;
    }
 
    
    public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
 
        return hash;
    }
 
    @Override
    public boolean equals(Object object) {
    if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
 
        BaseEntity other = (BaseEntity) object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
 
    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }
}