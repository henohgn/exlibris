-- begin LIBRARY_BOOK
alter table LIBRARY_BOOK add constraint FK_LIBRARY_BOOK_ON_LITERATURE_TYPE foreign key (LITERATURE_TYPE_ID) references LIBRARY_LITERATURE_TYPE(ID)^
create index IDX_LIBRARY_BOOK_ON_LITERATURE_TYPE on LIBRARY_BOOK (LITERATURE_TYPE_ID)^
-- end LIBRARY_BOOK
-- begin LIBRARY_BOOK_INSTANCE
alter table LIBRARY_BOOK_INSTANCE add constraint FK_LIBRARY_BOOK_INSTANCE_ON_BOOK_PUBLICATION foreign key (BOOK_PUBLICATION_ID) references LIBRARY_BOOK_PUBLICATION(ID)^
alter table LIBRARY_BOOK_INSTANCE add constraint FK_LIBRARY_BOOK_INSTANCE_ON_LIBRARY_DEPARTMENT foreign key (LIBRARY_DEPARTMENT_ID) references LIBRARY_LIBRARY_DEPARTMENT(ID)^
create index IDX_LIBRARY_BOOK_INSTANCE_ON_BOOK_PUBLICATION on LIBRARY_BOOK_INSTANCE (BOOK_PUBLICATION_ID)^
create index IDX_LIBRARY_BOOK_INSTANCE_ON_LIBRARY_DEPARTMENT on LIBRARY_BOOK_INSTANCE (LIBRARY_DEPARTMENT_ID)^
-- end LIBRARY_BOOK_INSTANCE
-- begin LIBRARY_BOOK_PUBLICATION
alter table LIBRARY_BOOK_PUBLICATION add constraint FK_LIBRARY_BOOK_PUBLICATION_ON_BOOK foreign key (BOOK_ID) references LIBRARY_BOOK(ID)^
alter table LIBRARY_BOOK_PUBLICATION add constraint FK_LIBRARY_BOOK_PUBLICATION_ON_PUBLISHER foreign key (PUBLISHER_ID) references LIBRARY_PUBLISHER(ID)^
alter table LIBRARY_BOOK_PUBLICATION add constraint FK_LIBRARY_BOOK_PUBLICATION_ON_TOWN foreign key (TOWN_ID) references LIBRARY_TOWN(ID)^
create index IDX_LIBRARY_BOOK_PUBLICATION_ON_BOOK on LIBRARY_BOOK_PUBLICATION (BOOK_ID)^
create index IDX_LIBRARY_BOOK_PUBLICATION_ON_PUBLISHER on LIBRARY_BOOK_PUBLICATION (PUBLISHER_ID)^
create index IDX_LIBRARY_BOOK_PUBLICATION_ON_TOWN on LIBRARY_BOOK_PUBLICATION (TOWN_ID)^
-- end LIBRARY_BOOK_PUBLICATION
-- begin LIBRARY_BOOK_AUTHOR_LINK
alter table LIBRARY_BOOK_AUTHOR_LINK add constraint FK_BOOAUT_ON_BOOK foreign key (BOOK_ID) references LIBRARY_BOOK(ID)^
alter table LIBRARY_BOOK_AUTHOR_LINK add constraint FK_BOOAUT_ON_AUTHOR foreign key (AUTHOR_ID) references LIBRARY_AUTHOR(ID)^
-- end LIBRARY_BOOK_AUTHOR_LINK
-- begin LIBRARY_CATEGORIES
create unique index IDX_LIBRARY_CATEGORIES_UK_NAME on LIBRARY_CATEGORIES (NAME) where DELETE_TS is null ^
-- end LIBRARY_CATEGORIES
-- begin LIBRARY_BOOK_CATEGORIES_LINK
alter table LIBRARY_BOOK_CATEGORIES_LINK add constraint FK_BOOCAT_ON_CATEGORIES foreign key (CATEGORIES_ID) references LIBRARY_CATEGORIES(ID)^
alter table LIBRARY_BOOK_CATEGORIES_LINK add constraint FK_BOOCAT_ON_BOOK foreign key (BOOK_ID) references LIBRARY_BOOK(ID)^
-- end LIBRARY_BOOK_CATEGORIES_LINK
-- begin LIBRARY_CATEGORIES_CATEGORIES_LINK
alter table LIBRARY_CATEGORIES_CATEGORIES_LINK add constraint FK_CATCAT_ON_CATEGORIES_1 foreign key (CATEGORIES_1_ID) references LIBRARY_CATEGORIES(ID)^
alter table LIBRARY_CATEGORIES_CATEGORIES_LINK add constraint FK_CATCAT_ON_CATEGORIES_2 foreign key (CATEGORIES_2_ID) references LIBRARY_CATEGORIES(ID)^
-- end LIBRARY_CATEGORIES_CATEGORIES_LINK
-- begin LIBRARY_FILES
alter table LIBRARY_FILES add constraint FK_LIBRARY_FILES_ON_FILE foreign key (FILE_ID) references SYS_FILE(ID)^
create index IDX_LIBRARY_FILES_ON_FILE on LIBRARY_FILES (FILE_ID)^
-- end LIBRARY_FILES
-- begin LIBRARY_BOOK_FILES_LINK
alter table LIBRARY_BOOK_FILES_LINK add constraint FK_BOOFIL_ON_FILES foreign key (FILES_ID) references LIBRARY_FILES(ID)^
alter table LIBRARY_BOOK_FILES_LINK add constraint FK_BOOFIL_ON_BOOK foreign key (BOOK_ID) references LIBRARY_BOOK(ID)^
-- end LIBRARY_BOOK_FILES_LINK
