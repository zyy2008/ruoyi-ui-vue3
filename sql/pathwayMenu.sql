-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地下水迁移途径信息', '1', '1', 'pathway', 'admin/pathway/index', 1, 0, 'C', '0', '0', 'admin:pathway:list', '#', 'admin', current_timestamp, '', null, '地下水迁移途径信息菜单');

-- 按钮父菜单ID
--SELECT 2084 := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 2084 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地下水迁移途径信息查询', 2084, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:pathway:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地下水迁移途径信息新增', 2084, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:pathway:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地下水迁移途径信息修改', 2084, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:pathway:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地下水迁移途径信息删除', 2084, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:pathway:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地下水迁移途径信息导出', 2084, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:pathway:export',       '#', 'admin', current_timestamp, '', null, '');