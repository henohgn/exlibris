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

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@NamePattern("%s|name")
@Table(name = "LIBRARY_BOOK")
@Entity(name = "library$Book")
public class Book extends StandardEntity {
    private static final long serialVersionUID = 2561809711185679867L;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @JoinTable(name = "LIBRARY_BOOK_FILES_LINK",
        joinColumns = @JoinColumn(name = "BOOK_ID"),
        inverseJoinColumns = @JoinColumn(name = "FILES_ID"))
    @ManyToMany
    protected List<Files> files;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LITERATURE_TYPE_ID")
    private LiteratureType literatureType;

    @JoinTable(name = "LIBRARY_BOOK_AUTHOR_LINK",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    @ManyToMany
    private Set<Author> authors;

    @JoinTable(name = "LIBRARY_BOOK_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @ManyToMany
    protected List<FileDescriptor> images;

    public void setImages(List<FileDescriptor> images) {
        this.images = images;
    }

    public List<FileDescriptor> getImages() {
        return images == null ? Collections.emptyList() : images;
    }


    public void setFiles(List<Files> files) {
        this.files = files;
    }

    public List<Files> getFiles() {
        return files == null ? Collections.emptyList() : files;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Author> getAuthors() {
        return authors == null ? Collections.emptySet() : authors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLiteratureType(LiteratureType literatureType) {
        this.literatureType = literatureType;
    }

    public LiteratureType getLiteratureType() {
        return literatureType;
    }
}