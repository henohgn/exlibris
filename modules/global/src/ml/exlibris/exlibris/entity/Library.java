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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

@NamePattern("%s|name")
@Table(name = "LIBRARY_LIBRARY")
@Entity(name = "library$Library")
public class Library extends StandardEntity {
    private static final long serialVersionUID = -1756761790726425957L;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;


    @JoinTable(name = "LIBRARY_LIBRARY_CATEGORIES_LINK",
        joinColumns = @JoinColumn(name = "LIBRARY_ID"),
        inverseJoinColumns = @JoinColumn(name = "CATEGORIES_ID"))
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToMany
    protected List<Categories> categories;

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Categories> getCategories() {
        return categories;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}