import KpiData from "../dtos/KpiDataDTO";

export interface KpiRepository {
    fetchKpis(): Promise<KpiData>;
}
