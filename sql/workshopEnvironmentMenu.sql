-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('生产车间分布与环境情况', '1', '1', 'workshopEnvironment', 'admin/workshopEnvironment/index', 1, 0, 'C', '0', '0', 'admin:workshopEnvironment:list', '#', 'admin', current_timestamp, '', null, '生产车间分布与环境情况菜单');

-- 按钮父菜单ID
--SELECT @parentId := LAST_INSERT_ID();
select max(menu_id) from sys_menu;

/*
   全局替换 @parentId 为 上面语句查询出来的 max(menu_id)
*/

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('生产车间分布与环境情况查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'admin:workshopEnvironment:query',        '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('生产车间分布与环境情况新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'admin:workshopEnvironment:add',          '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('生产车间分布与环境情况修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'admin:workshopEnvironment:edit',         '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('生产车间分布与环境情况删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'admin:workshopEnvironment:remove',       '#', 'admin', current_timestamp, '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('生产车间分布与环境情况导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'admin:workshopEnvironment:export',       '#', 'admin', current_timestamp, '', null, '');