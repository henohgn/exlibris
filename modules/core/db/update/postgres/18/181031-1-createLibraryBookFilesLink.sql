create table LIBRARY_BOOK_FILES_LINK (
    FILES_ID uuid,
    BOOK_ID uuid,
    primary key (FILES_ID, BOOK_ID)
);
