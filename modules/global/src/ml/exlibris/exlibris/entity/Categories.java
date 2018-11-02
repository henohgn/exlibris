package ml.exlibris.exlibris.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@NamePattern("%s|name")
@Table(name = "LIBRARY_CATEGORIES")
@Entity(name = "library$Categories")
public class Categories extends StandardEntity {
    private static final long serialVersionUID = 4095379131102261760L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @JoinTable(name = "LIBRARY_CATEGORIES_CATEGORIES_LINK",
        joinColumns = @JoinColumn(name = "CATEGORIES_1_ID"),
        inverseJoinColumns = @JoinColumn(name = "CATEGORIES_2_ID"))
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.DENY)
    @ManyToMany
    protected List<Categories> related;


    public void setRelated(List<Categories> related) {
        this.related = related;
    }

    public List<Categories> getRelated() {
        return related;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}