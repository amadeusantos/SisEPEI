import { useQuery } from '@tanstack/react-query'

import { getProfiles } from '../services/ProfileService'

export function useProfiles() {
    return useQuery({
        queryKey: ['profiles'],
        queryFn: getProfiles
    })
}
