import { Container } from "inversify";
import { TYPES } from "./types";
import { NotificationsRepository, Category, MessagesRepository } from "./interfaces";
import { KpiRepositoryImpl, NotificationsRepositoryImpl, MessagesRepositoryImpl as MessagesRepositoryImpl } from "./entities";
import { KpiRepository } from "../repositories/KpiRepository";
import { CATEGORIES, API_BASE_URL } from "../constants/constants";

const myContainer = new Container();
myContainer.bind<KpiRepository>(TYPES.KpiRepository).to(KpiRepositoryImpl);
myContainer.bind<NotificationsRepository>(TYPES.NotificationsRepository).to(NotificationsRepositoryImpl);
myContainer.bind<MessagesRepository>(TYPES.MessagesRepository).to(MessagesRepositoryImpl);
myContainer.bind<string>(TYPES.ApiBaseUrl).toConstantValue(API_BASE_URL);
myContainer.bind<Category[]>(TYPES.Categories).toConstantValue(CATEGORIES);

export { myContainer };
