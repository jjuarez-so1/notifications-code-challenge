insert into topics (name) values ('SPORTS');
insert into topics (name) values ('FINANCE');
insert into topics (name) values ('FILMS');

insert into broadcast_channels (name) values ('SMS');
insert into broadcast_channels (name) values ('EMAIL');
insert into broadcast_channels (name) values ('PUSH_NOTIFICATION');

insert into users (email, name, phone) values ('jjuarezitc@gmail.com', 'joseph juarez', '2722369078');
insert into users (email, name, phone) values ('fabulautenticastica@gmail.com', 'fabiola estrada', '5577213904');
insert into users (email, name, phone) values ('user3@gmail.com', 'user3', '5577213901');
insert into users (email, name, phone) values ('user4@gmail.com', 'user4', '5577213902');
insert into users (email, name, phone) values ('user5@gmail.com', 'user5', '5577213903');
insert into users (email, name, phone) values ('user6@gmail.com', 'user6', '5577213904');
insert into users (email, name, phone) values ('user7@gmail.com', 'user7', '5577213905');
insert into users (email, name, phone) values ('user8@gmail.com', 'user8', '5577213906');
insert into users (email, name, phone) values ('user9@gmail.com', 'user9', '5577213907');
insert into users (email, name, phone) values ('user10@gmail.com', 'user10', '5577213908');
insert into users (email, name, phone) values ('user11-push@gmail.com', 'user-push', '5577213908');

-- user push
insert into user_preferred_channels (channel_id, user_id) values (3,11);

-- joseph
insert into user_preferred_channels (channel_id, user_id) values (1,1);
insert into user_preferred_channels (channel_id, user_id) values (2,1);
insert into user_preferred_channels (channel_id, user_id) values (3,1);

-- fabiola
insert into user_preferred_channels (channel_id, user_id) values (1,2);
insert into user_preferred_channels (channel_id, user_id) values (2,2);
insert into user_preferred_channels (channel_id, user_id) values (3,2);

-- user 3
insert into user_preferred_channels (channel_id, user_id) values (1,3);
insert into user_preferred_channels (channel_id, user_id) values (2,3);
insert into user_preferred_channels (channel_id, user_id) values (3,3);

-- user 4
insert into user_preferred_channels (channel_id, user_id) values (1,4);
insert into user_preferred_channels (channel_id, user_id) values (2,4);
insert into user_preferred_channels (channel_id, user_id) values (3,4);

-- user 5
insert into user_preferred_channels (channel_id, user_id) values (1,5);
insert into user_preferred_channels (channel_id, user_id) values (2,5);
insert into user_preferred_channels (channel_id, user_id) values (3,5);

-- user 6
insert into user_preferred_channels (channel_id, user_id) values (1,6);
insert into user_preferred_channels (channel_id, user_id) values (2,6);
insert into user_preferred_channels (channel_id, user_id) values (3,6);

-- user 7
insert into user_preferred_channels (channel_id, user_id) values (1,7);
insert into user_preferred_channels (channel_id, user_id) values (2,7);
insert into user_preferred_channels (channel_id, user_id) values (3,7);

-- user 8
insert into user_preferred_channels (channel_id, user_id) values (1,8);
insert into user_preferred_channels (channel_id, user_id) values (2,8);
insert into user_preferred_channels (channel_id, user_id) values (3,8);

-- user 9
insert into user_preferred_channels (channel_id, user_id) values (1,9);
insert into user_preferred_channels (channel_id, user_id) values (2,9);
insert into user_preferred_channels (channel_id, user_id) values (3,9);

-- user 10
insert into user_preferred_channels (channel_id, user_id) values (1,10);
insert into user_preferred_channels (channel_id, user_id) values (2,10);
insert into user_preferred_channels (channel_id, user_id) values (3,10);

-- subscribe to topics
insert into user_subscribed_topics (topic_id, user_id) values (2, 1);
insert into user_subscribed_topics (topic_id, user_id) values (2, 2);
insert into user_subscribed_topics (topic_id, user_id) values (2, 3);
insert into user_subscribed_topics (topic_id, user_id) values (2, 4);
insert into user_subscribed_topics (topic_id, user_id) values (2, 5);
insert into user_subscribed_topics (topic_id, user_id) values (2, 6);
insert into user_subscribed_topics (topic_id, user_id) values (2, 7);
insert into user_subscribed_topics (topic_id, user_id) values (2, 8);
insert into user_subscribed_topics (topic_id, user_id) values (2, 9);
insert into user_subscribed_topics (topic_id, user_id) values (2, 10);

-- subscribe usr push to finance
insert into user_subscribed_topics (topic_id, user_id) values (2, 11);
