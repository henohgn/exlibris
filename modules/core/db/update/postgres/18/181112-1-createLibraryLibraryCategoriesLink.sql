create table LIBRARY_LIBRARY_CATEGORIES_LINK (
    CATEGORIES_ID uuid,
    LIBRARY_ID uuid,
    primary key (CATEGORIES_ID, LIBRARY_ID)
);
