import { handleObject } from '@/utils';
import api1 from './api1';

type APP1 = UnionToIntersection<(typeof api1)[keyof typeof api1]>;

export default { ...(handleObject(api1) as APP1) };
