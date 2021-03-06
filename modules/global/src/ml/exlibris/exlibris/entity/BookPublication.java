/*
 * Copyright (c) 2017 Haulmont
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ml.exlibris.exlibris.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s|book")
@Table(name = "LIBRARY_BOOK_PUBLICATION")
@Entity(name = "library$BookPublication")
public class BookPublication extends StandardEntity {
    private static final long serialVersionUID = -4909470921229152146L;

    @Column(name = "YEAR_", nullable = false)
    private Integer year;

    @Column(name = "ISBN", length = 13)
    protected String isbn;

    @Column(name = "CIRCULATION")
    protected Integer circulation;

    @Column(name = "PAGES")
    protected Integer pages;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOWN_ID")
    private Town town;

    @JoinTable(name = "LIBRARY_BOOK_PUBLICATION_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "BOOK_PUBLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @ManyToMany
    protected List<FileDescriptor> images;

    public void setImages(List<FileDescriptor> images) {
        this.images = images;
    }

    public List<FileDescriptor> getImages() {
        return images;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setCirculation(Integer circulation) {
        this.circulation = circulation;
    }

    public Integer getCirculation() {
        return circulation;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPages() {
        return pages;
    }


    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Town getTown() {
        return town;
    }

}