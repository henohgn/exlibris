package ml.exlibris.exlibris.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.FileDescriptor;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@NamePattern("%s|description")
@Table(name = "LIBRARY_FILES")
@Entity(name = "library$Files")
public class Files extends StandardEntity {
    private static final long serialVersionUID = 6412646558349553874L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID")
    protected FileDescriptor file;

    @Column(name = "DESCRIPTION")
    protected String description;

    @JoinTable(name = "LIBRARY_BOOK_FILES_LINK",
        joinColumns = @JoinColumn(name = "FILES_ID"),
        inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    @ManyToMany
    protected List<Book> books;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }


    public void setFile(FileDescriptor file) {
        this.file = file;
    }

    public FileDescriptor getFile() {
        return file;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}