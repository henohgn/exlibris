alter table LIBRARY_LIBRARY_DEPARTMENT_CATEGORIES_LINK add constraint FK_LIBDEPCAT_ON_CATEGORIES foreign key (CATEGORIES_ID) references LIBRARY_CATEGORIES(ID);
alter table LIBRARY_LIBRARY_DEPARTMENT_CATEGORIES_LINK add constraint FK_LIBDEPCAT_ON_LIBRARY_DEPARTMENT foreign key (LIBRARY_DEPARTMENT_ID) references LIBRARY_LIBRARY_DEPARTMENT(ID);