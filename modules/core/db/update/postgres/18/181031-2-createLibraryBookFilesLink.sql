alter table LIBRARY_BOOK_FILES_LINK add constraint FK_BOOFIL_ON_FILES foreign key (FILES_ID) references LIBRARY_FILES(ID);
alter table LIBRARY_BOOK_FILES_LINK add constraint FK_BOOFIL_ON_BOOK foreign key (BOOK_ID) references LIBRARY_BOOK(ID);
