-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理', '2006', '1', 'manage', 'admin/manage/index', 1, 0, 'C', '0', '0', 'admin:manage:list', '#', 'admin', current_timestamp, '', null, '项目管理菜单');

-- 按钮父菜单ID
--SELECT @parentId := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 @parentId 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:manage:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:manage:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:manage:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:manage:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:manage:export',       '#', 'admin', current_timestamp, '', null, '');