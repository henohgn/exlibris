create table LIBRARY_BOOK_FILE_DESCRIPTOR_LINK (
    BOOK_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (BOOK_ID, FILE_DESCRIPTOR_ID)
);
