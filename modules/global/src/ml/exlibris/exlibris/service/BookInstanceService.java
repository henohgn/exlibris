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

package ml.exlibris.exlibris.service;

import ml.exlibris.exlibris.entity.BookInstance;
import ml.exlibris.exlibris.entity.BookPublication;
import ml.exlibris.exlibris.entity.Library;
import com.haulmont.cuba.core.global.View;

import java.util.Collection;

public interface BookInstanceService {

    String NAME = "library_BookInstanceService";

    /**
     * Create and return new book instances.
     */
    Collection<BookInstance> createBookInstances(BookPublication bookPublication, Integer amount);

    /**
     * Set department attribute in book instances and return updated entities.
     */
    Collection<BookInstance> assignLibrary(Collection<BookInstance> bookInstances, Library libraryDepartment, View bookInstanceView);
}