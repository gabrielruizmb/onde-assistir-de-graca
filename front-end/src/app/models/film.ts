import { Category } from "./category";
import { Channel } from "./channel";

export class Film {
    id!: string;
    title!: string;
    year!: number;
    posterUrl!: string;
    description!: string;
    category!: Category;
    channels: Channel[] = [];
}
