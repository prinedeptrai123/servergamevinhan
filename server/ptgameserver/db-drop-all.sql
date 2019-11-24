alter table db_user_building drop foreign key fk_db_user_building_user_model_id;
drop index ix_db_user_building_user_model_id on db_user_building;

alter table db_user_building drop foreign key fk_db_user_building_building_model_id;
drop index ix_db_user_building_building_model_id on db_user_building;

alter table db_user_trooper drop foreign key fk_db_user_trooper_user_model_id;
drop index ix_db_user_trooper_user_model_id on db_user_trooper;

alter table db_user_trooper drop foreign key fk_db_user_trooper_trooper_model_id;
drop index ix_db_user_trooper_trooper_model_id on db_user_trooper;

drop table if exists db_building;

drop table if exists db_user_building;

drop table if exists db_user_trooper;

drop table if exists db_trooper;

drop table if exists db_user;

