package ml.exlibris.exlibris.web.book;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ml.exlibris.exlibris.entity.Book;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class BookEdit extends AbstractEditor<Book> {


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

    private Book book;
    private boolean isModified;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        multiUpload.addQueueUploadCompleteListener(() -> {
            for (Map.Entry<UUID, String> entry : multiUpload.getUploadsMap().entrySet()) {
                UUID fileId = entry.getKey();
                String fileName = entry.getValue();
                FileDescriptor fd = fileUploadingAPI.getFileDescriptor(fileId, fileName);
                // save file to FileStorage
                try {
                    fileUploadingAPI.putFileIntoStorage(fileId, fd);
                } catch (FileStorageException e) {
                    new RuntimeException("Error saving file to FileStorage", e);
                }
                // save file descriptor to database
                FileDescriptor committedFd = dataSupplier.commit(fd);

                addThumbnail(committedFd);
                book.getImages().add(fd);
                isModified = true;
            }
            multiUpload.clearUploads();

        });


        multiUpload.addFileUploadErrorListener(event ->
                showNotification("File upload error", NotificationType.HUMANIZED));

    }

    @Override
    public void ready() {
        super.ready();
        book = getItem();

        getAndShowBookImages();
    }

    private void getAndShowBookImages() {
        book.getImages().forEach(this::addThumbnail);
    }

    private void addThumbnail(FileDescriptor fd) {
        Image image = componentsFactory.createComponent(Image.class);
        image.setSource(FileDescriptorResource.class).setFileDescriptor(fd);
        image.setWidth("100px");
        image.setScaleMode(Image.ScaleMode.SCALE_DOWN);
        image.setHeight("100px");

        HBoxLayout boxLayout = componentsFactory.createComponent(HBoxLayout.class);
        boxLayout.add(image);

        Button button = componentsFactory.createComponent(Button.class);
        button.setCaption("X");
        button.setAction(new BaseAction("Remove") {
            @Override
            public void actionPerform(Component component) {
                try {
                    fileLoader.removeFile(fd);
                    book.getImages().remove(fd);
                    isModified = true;
                } catch (FileStorageException ignore) {
                }
                thumbnailsBox.remove(boxLayout);
            }
        });
        boxLayout.add(button);

        thumbnailsBox.add(boxLayout);
    }

    @Override
    public void commitAndClose() {
        if (isModified) dataSupplier.commit(book);
        super.commitAndClose();
    }
}