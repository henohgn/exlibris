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

package ml.exlibris.exlibris.web.bookpublication;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ml.exlibris.exlibris.entity.BookPublication;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookPublicationEdit extends AbstractEditor<BookPublication> {
    
    @Inject
    protected FieldGroup fieldGroup;
    @Inject
    private FileMultiUploadField multiUpload;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private DataSupplier dataSupplier;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private FlowBoxLayout thumbnailsBox;
    @Inject
    private FileLoader fileLoader;
    @Inject
    private EntityStates entityStates;

    private BookPublication bookPublication;
    private boolean isModified;
    private List<FileDescriptor> imagesToDelete = new ArrayList<>();

    @Override
    protected void postInit() {
        if (!PersistenceHelper.isNew(getItem()) || getItem().getBook() != null) {
            fieldGroup.getFieldNN("book").setEditable(false);
        }
        bookPublication = getItem();
        getAndShowBookImages();
    }


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        multiUpload.addQueueUploadCompleteListener(() -> {
            for (Map.Entry<UUID, String> entry : multiUpload.getUploadsMap().entrySet()) {
                UUID fileId = entry.getKey();
                String fileName = entry.getValue();
                FileDescriptor fd = fileUploadingAPI.getFileDescriptor(fileId, fileName);
                // save file to FileStorage
                if (entityStates.isNew(bookPublication)) {
                    saveNewEntity();
                }
                try {
                    fileUploadingAPI.putFileIntoStorage(fileId, fd);
                } catch (FileStorageException ignore) {
                }

                // save file descriptor to database
                FileDescriptor committedFd = dataSupplier.commit(fd);

                addThumbnail(committedFd);
                bookPublication.getImages().add(fd);
                isModified = true;
            }
            multiUpload.clearUploads();

        });


        multiUpload.addFileUploadErrorListener(event ->
                showNotification("File upload error", NotificationType.HUMANIZED));

    }

    private void getAndShowBookImages() {
        bookPublication.getImages().forEach(this::addThumbnail);
    }

    private void addThumbnail(FileDescriptor fd) {
        Image image = componentsFactory.createComponent(Image.class);
        image.setSource(FileDescriptorResource.class).setFileDescriptor(fd);
        image.setWidth("200px");
        image.setScaleMode(Image.ScaleMode.SCALE_DOWN);
        image.setHeight("200px");

        HBoxLayout imageBox = componentsFactory.createComponent(HBoxLayout.class);
        imageBox.add(image);
        VBoxLayout innerButtonBox = componentsFactory.createComponent(VBoxLayout.class);
        imageBox.add(innerButtonBox);

        Button clearButton = componentsFactory.createComponent(Button.class);
        clearButton.setCaption("X");
        clearButton.setAction(new BaseAction("Remove") {
            @Override
            public void actionPerform(Component component) {
                bookPublication.getImages().remove(fd);
                imagesToDelete.add(fd);
                isModified = true;
                thumbnailsBox.remove(imageBox);
            }
        });
        innerButtonBox.add(clearButton);

        Button showButton = componentsFactory.createComponent(Button.class);
        showButton.setCaption(getMessage("show"));
        showButton.setAction(new BaseAction("show") {
            @Override
            public void actionPerform(Component component) {
                AppBeans.get(ExportDisplay.class).show(fd);
            }
        });
        innerButtonBox.add(showButton);

        thumbnailsBox.add(imageBox);
    }

    private void saveNewEntity() {
        bookPublication = dataSupplier.commit(bookPublication);
        getDsContext().refresh();
    }

    @Override
    public void commitAndClose() {
        if (isModified) {
            imagesToDelete.forEach(img -> {
                try {
                    fileLoader.removeFile(img);
                } catch (FileStorageException ignore) {
                }
            });
            dataSupplier.commit(bookPublication);
        }
        super.commitAndClose();
    }
}